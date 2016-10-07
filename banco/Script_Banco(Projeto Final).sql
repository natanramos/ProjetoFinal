
create table public.usuarios(
id bigint not null,
nome varchar(100) not null,
data_cadastro date not null,
email varchar(100),
login varchar(50) not null,
senha varchar(20) not null,
primary key(id)
);

create sequence public.seq_usuarios;

create table public.estados(
id bigint not null,
codigo_ibge integer,
sigla varchar(2) not null,
nome varchar(100) not null,
primary key(id)
);

create sequence public.seq_estados;

Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(1,12,'AC','Acre');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(2,27,'AL','Alagoas');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(3,13,'AM','Amazonas');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(4,16,'AP','Amapá');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(5,29,'BA','Bahia');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(6,23,'CE','Ceará');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(7,53,'DF','Distrito Federal');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(8,32,'ES','Espírito Santo');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(9,52,'GO','Goiás');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(10,21,'MA','Maranhão');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(11,31,'MG','Minas Gerais');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(12,50,'MS','Mato Grosso do Sul');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(13,51,'MT','Mato Grosso');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(14,15,'PA','Pará');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(15,25,'PB','Paraíba');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(16,26,'PE','Pernambuco');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(17,22,'PI','Piauí');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(18,41,'PR','Paraná');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(19,33,'RJ','Rio de Janeiro');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(20,24,'RN','Rio Grande do Norte');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(21,11,'RO','Rondônia');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(22,14,'RR','Roraima');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(23,43,'RS','Rio Grande do Sul');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(24,42,'SC','Santa Catarina');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(25,28,'SE','Sergipe');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(26,35,'SP','São Paulo');
Insert Into public.estados (id,codigo_ibge,sigla,nome) Values(27,17,'TO','Tocantins');

create table public.municipios(
id bigint not null,
id_estados integer not null,
nome varchar(100) not null,
foreign key (id_estados) references public.estados (id) on delete restrict,
primary key(id)
);

create sequence public.seq_municipios;

Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),24,'Criciúma');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),24,'Santa Rosa do Sul');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),24,'Içara');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),24,'Florianópolis');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),24,'Blumenau');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),24,'Araranguá');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),24,'Sombrio');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),24,'Chapecó');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),24,'Lages');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),23,'Porto Alegre');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),23,'Torres');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),23,'Capão da Canoa');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),23,'Gravataí');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),23,'Caxias do Sul');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),23,'Canela');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),23,'Gramado');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),23,'Tramandaí');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),18,'Curitiba');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),18,'Cascavel');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),18,'Maringá');
Insert Into public.municipios (id,id_estados,nome) Values((select nextval('seq_municipios')),18,'Telêmaco Borba');

create table public.pessoas(
id bigint not null,
nome varchar(200) not null,
tipo_pessoa varchar(1) not null,
documento varchar(14),
data_nascimento date,
telefone varchar(20) not null,
email varchar(100),
rua varchar(200),
numero varchar(20),
id_municipios integer,
id_estados integer,
foreign key (id_municipios) references public.municipios (id) on delete restrict,
foreign key (id_estados) references public.estados (id) on delete restrict,
primary key(id)
);

create sequence public.seq_pessoas;

create table public.controles(
id bigint not null,
mensalista varchar(1) not null,
id_pessoas integer,
placa varchar(6) not null,
marca varchar(2),
modelo varchar(100),
cor varchar(50),
responsavel varchar(100),
data_hora_entrada timestamp,
data_hora_saida timestamp,
situacao varchar(1) not null,
valor numeric(15,2) not null,
foreign key (id_pessoas) references public.pessoas (id) on delete restrict,
primary key(id)
);

create sequence public.seq_controles;

create table public.competencias(
id bigint not null,
descricao varchar(100) not null,
data_inicial date not null,
data_final date not null,
data_vencimento date not null,
primary key(id)
);

create sequence public.seq_competencias;

create table public.debitos(
id bigint not null,
id_competencias integer not null,
id_pessoas integer not null,
data_vencimento date not null,
valor numeric(15,2) not null,
foreign key (id_competencias) references public.competencias (id) on delete restrict,
primary key(id)
);

create sequence public.seq_debitos;

create table public.debitos_controles(
id bigint not null,
id_debitos integer not null,
id_controles integer not null,
foreign key (id_debitos) references public.debitos (id) on delete restrict,
foreign key (id_controles) references public.controles (id) on delete restrict,
primary key(id)
);

create sequence public.seq_debitos_controles;

--drop sequence public.seq_debitos_controles;
--drop table public.debitos_controles;
--drop sequence public.seq_debitos;
--drop table public.debitos;
--drop sequence public.seq_competencias;
--drop table public.competencias;
--drop sequence public.seq_controles;
--drop table public.controles;
--drop sequence public.seq_pessoas;
--drop table public.pessoas;
--drop sequence public.seq_municipios;
--drop table public.municipios;
--drop sequence public.seq_estados;
--drop table public.estados;
--drop sequence public.seq_usuarios;
--drop table public.usuarios;