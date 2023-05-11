DROP database IF exists Futbol;
create database Futbol;
USE Futbol;

create table Equipo(
Puntos int,
Nombre varchar (50) primary key,
N_Jugadores int
);

create table Jugador(
Cod_Jugador int primary key,
Nombre varchar (50),
Nombre_equipo varchar (50),
Edad int,
constraint Jug foreign key (Nombre_equipo) references Equipo (Nombre) on delete cascade on update cascade
);

create table Participa(
N_Asistencias int,
N_Goles int,
Cod_Jugador int,
Nombre_Equipo varchar(50),
constraint fk foreign key (Cod_Jugador) references Jugador (Cod_Jugador) on delete cascade,
constraint fk1 foreign key (Nombre_equipo) references Equipo (Nombre) on delete cascade on update cascade,
constraint fk2 primary key (Nombre_equipo, Cod_Jugador)
);

create table Partido(
Duracion int,
Cod_Partido int primary key
);

create table Estadio(
Aforo int,
Nombre varchar (50) primary key,
Fecha_Construccion date
);

create table Juega(
Nombre_Equipo varchar (50),
Cod_Partido int,
Nombre_Estadio varchar (50),
constraint Jue foreign key (Nombre_Equipo) references Equipo (Nombre) on delete cascade on update cascade,
constraint Jue2 foreign key (Cod_Partido) references Partido (Cod_Partido) on delete cascade on update cascade,
constraint Jue3 foreign key (Nombre_Estadio) references Estadio (Nombre) on delete cascade on update cascade,
constraint Jue4 primary key (Nombre_Equipo, Cod_Partido, Nombre_Estadio)
);

/*1. He decidido hacer esta restricción para limitar o controlar el número de personas que
pueden entrar al estadio*/
alter table Estadio add constraint capacidad  check (Aforo <= 100000);

/*2. Esta restricción la he hecho para establecer un máximo de puntos a conseguir
en la liga, como son 30 partidos el límite está en 90*/
alter table Equipo add constraint puntaje check (Puntos <= 90);

/*3. Esta restricción la he incluido para controlar la edad de los jugadores que van a
participar en la competición*/
alter table Jugadores add constraint limite_edad check (Edad between 18 and 35);

/*4. Esta restricción es para establecer que el número de goles y de asistencias no sea negativo*/
alter table Participa add constraint menor_cero check (N_Goles != 0);
alter table Participa add constraint menor_cero1 check (N_Asistencias != 0);

/*5. Añado esta restricción para controlar que un partido no dure más de 90 minutos*/
alter table Partido add constraint minutaje check (Duracion <= 98);

insert into Equipo values
(32, 'FC Barcelona', 32),
(65, 'Real Madrid', 32),
(47, 'Real Betis Balonpie',	32),
(53, 'Sevilla FC', 32),
(77, 'Atletico de Madrid',	32);

insert into Jugadores values
(1, 'Ansu Fati' , 'FC Barcelona',18),
(2,'Benzema', 'Real Madrid',35),
(3, 'Fekir', 'Real Betis Balonpie',20),
(4, 'Ocampos', 'Sevilla FC',27),
(5,'Griezzman', 'Atletico de Madrid' , 24);

insert into Partido values
(90, 1),
(90, 2),
(92, 3),
(94, 4),
(98, 5);

insert into Participa values
(30,12,1,'FC Barcelona'),
(14,20,2,'Real Madrid'),
(21,13,3,'Real Betis Balonpie'),
(2,30,4,'Sevilla FC'),
(8,22,5,'Atletico de Madrid');

insert into Estadio values
(99354, 'Camp Nou', '1957-09-24'),
(81044, 'Santiago Bernabeu', '1947-12-14'),
(60721, 'Benito Villamarin', '1929-03-17'),
(43864, 'Sanchez Pizjuan', '1958-09-07'),
(68456, 'Civitas Metropolitano', '1994-09-04');

insert into Juega values
('FC Barcelona', 1, 'Camp Nou'),
('Real Madrid', 2, 'Santiago Bernabeu'),
('Real Betis Balonpie', 3, 'Benito Villamarin'),
('Sevilla FC', 4, 'Sanchez Pizjuan'),
('Atletico de Madrid', 5, 'Civitas Metropolitano');

