create database if not exists universidad;
use universidad;

create table alumnos (
    id int primary key auto_increment,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    ci varchar(20) unique not null,
    fecha_nacimiento date not null,
    email varchar(150) unique not null
);

create table docentes (
    id int primary key auto_increment,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    ci varchar(20) unique not null,
    especialidad varchar(150)
);

create table materias (
    id int primary key auto_increment,
    nombre varchar(150) not null,
    codigo varchar(20) unique not null,
    cupo_maximo int not null,
    id_docente int,

    foreign key(id_docente)
        references docentes(id)
);

create table inscripciones (
    id int primary key auto_increment,
    id_alumno int not null,
    id_materia int not null,
    fecha_inscripcion date not null,

    unique (id_alumno,id_materia),
    foreign key(id_alumno)
        references alumnos(id) on delete cascade,
    foreign key(id_materia)
        references materias(id)

);

create table calificaciones (
    id int primary key auto_increment,
    id_inscripcion int not null,
    nota decimal(4,2) not null,
    fecha date not null,

    foreign key(id_inscripcion)
        references inscripciones(id) on delete cascade

);