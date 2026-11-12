CREATE DATABASE caredrop;

USE caredrop;

CREATE TABLE casa_repouso (
id_casa INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(120) NOT NULL,
cnpj VARCHAR(18) NOT NULL,
endereco VARCHAR(200) NOT NULL,
telefone VARCHAR(20) NOT NULL,
criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE usuario (
id_usuario INT AUTO_INCREMENT PRIMARY KEY,
id_casa INT NOT NULL,
nome VARCHAR(120) NOT NULL,
email VARCHAR(150) NOT NULL,
senha_hash VARCHAR(255) NOT NULL,
tipo ENUM('ADMIN', 'CUIDADOR') NOT NULL,
ativo BOOLEAN DEFAULT TRUE,

UNIQUE (id_casa, email),

CONSTRAINT FOREIGN KEY (id_casa) REFERENCES casa_repouso(id_casa)
);

CREATE TABLE residente (
id_residente INT AUTO_INCREMENT PRIMARY KEY,
id_casa INT NOT NULL,
nome VARCHAR(120) NOT NULL,
data_nascimento DATE NOT NULL,
quarto VARCHAR(20),
observacoes TEXT,
ativo BOOLEAN DEFAULT TRUE,

UNIQUE (id_residente, id_casa),

CONSTRAINT FOREIGN KEY (id_casa) REFERENCES casa_repouso(id_casa)
);

CREATE TABLE medicamento (
    id_medicamento INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    principio_ativo VARCHAR(120),
    apresentacao VARCHAR(100)
);

CREATE TABLE prescricao (
    id_prescricao INT AUTO_INCREMENT PRIMARY KEY,
    id_casa INT NOT NULL,
    id_residente INT NOT NULL,
    id_medicamento INT NOT NULL,
    dosagem VARCHAR(80) NOT NULL,
    instrucoes TEXT,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    ativo BOOLEAN DEFAULT TRUE,

    CONSTRAINT fk_prescricao_casa
        FOREIGN KEY (id_casa)
        REFERENCES casa_repouso(id_casa),

    CONSTRAINT fk_prescricao_residente
        FOREIGN KEY (id_residente)
        REFERENCES residente(id_residente),

    CONSTRAINT fk_prescricao_medicamento
        FOREIGN KEY (id_medicamento)
        REFERENCES medicamento(id_medicamento)
);

CREATE TABLE agendamento (
    id_agendamento INT AUTO_INCREMENT PRIMARY KEY,
    id_casa INT NOT NULL,
    id_prescricao INT NOT NULL,

    data_hora_prevista DATETIME NOT NULL,
    data_hora_administracao DATETIME NULL,

    status ENUM(
        'PENDENTE',
        'ADMINISTRADO',
        'ATRASADO',
        'NAO_ADMINISTRADO'
    ) DEFAULT 'PENDENTE',

    CONSTRAINT fk_agendamento_casa
        FOREIGN KEY (id_casa)
        REFERENCES casa_repouso(id_casa),

    CONSTRAINT fk_agendamento_prescricao
        FOREIGN KEY (id_prescricao)
        REFERENCES prescricao(id_prescricao)
);

CREATE TABLE administracao (
    id_administracao INT AUTO_INCREMENT PRIMARY KEY,
    id_agendamento INT NOT NULL,
    id_casa INT NOT NULL,
    id_cuidador INT NOT NULL,

    data_hora_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
    observacao TEXT,

    CONSTRAINT uk_administracao_agendamento
        UNIQUE (id_agendamento),

    CONSTRAINT fk_administracao_agendamento
        FOREIGN KEY (id_agendamento)
        REFERENCES agendamento(id_agendamento),

    CONSTRAINT fk_administracao_casa
        FOREIGN KEY (id_casa)
        REFERENCES casa_repouso(id_casa),

    CONSTRAINT fk_administracao_cuidador
        FOREIGN KEY (id_cuidador)
        REFERENCES usuario(id_usuario)
);



