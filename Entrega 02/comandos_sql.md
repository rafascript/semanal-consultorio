# Criação da base de dados

```
CREATE DATABASE consultorio_medico;
\c consultorio_medico;


CREATE TABLE enderecos (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    estado VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    rua VARCHAR(150) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    cep VARCHAR(15) NOT NULL
);


CREATE TABLE pacientes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL,
    endereco_id UUID,
    CONSTRAINT fk_endereco
        FOREIGN KEY (endereco_id)
        REFERENCES enderecos(id)
        ON DELETE SET NULL
);


CREATE TYPE status_consulta AS ENUM ('AGENDADA', 'CONFIRMADA', 'CANCELADA', 'REALIZADA');


CREATE TABLE consultas (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    agendamento TIMESTAMP NOT NULL,
    status status_consulta DEFAULT 'AGENDADA',
    paciente_id UUID,
    CONSTRAINT fk_paciente
        FOREIGN KEY (paciente_id)
        REFERENCES pacientes(id)
        ON DELETE SET NULL
);

```

# Índices

```
CREATE INDEX idx_pacientes_cpf ON pacientes(cpf);

CREATE INDEX idx_consultas_agendamento ON consultas(agendamento);

CREATE INDEX idx_enderecos_cidade ON enderecos(cidade);
```

# TRIGGER DE AUTOMAÇÃO

```

CREATE TABLE log_consultas (
    id SERIAL PRIMARY KEY,
    consulta_id UUID,
    data_evento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    mensagem TEXT
);


CREATE OR REPLACE FUNCTION registrar_cancelamento()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.status = 'CANCELADA' AND OLD.status <> 'CANCELADA' THEN
        INSERT INTO log_consultas (consulta_id, mensagem)
        VALUES (NEW.id, 'Consulta cancelada.');
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_registra_cancelamento
AFTER UPDATE ON consultas
FOR EACH ROW
EXECUTE FUNCTION registrar_cancelamento();

```

# SELECTS 

```

SELECT p.nome, p.cpf, e.cidade
FROM pacientes p
JOIN enderecos e ON p.endereco_id = e.id;


SELECT c.id, c.agendamento, c.status, p.nome AS paciente
FROM consultas c
JOIN pacientes p ON c.paciente_id = p.id
WHERE c.status IN ('AGENDADA', 'CONFIRMADA')
  AND c.agendamento BETWEEN NOW() AND NOW() + INTERVAL '7 days';


SELECT status, COUNT(*) AS total
FROM consultas
GROUP BY status;

```