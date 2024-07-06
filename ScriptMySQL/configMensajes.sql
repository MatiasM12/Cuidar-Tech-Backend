use vdg;

insert INTO configmensaje ( asunto, mensajeAft, mensajeBef, tipo) VALUES
( 'Nueva contraseña', 'Muchas gracias!', 'Esta es su nueva contraseña:','passMail'),
( null, 'muchas gracias!', 'Ha ocurrido una violación de la restricción:', 'alertaTelegram'),
( null, 'Muchas gracias!', 'Ha ocurrido una violación de la restricción:','alertaWsp'),
('Se activo el botón de panico', 'Activo el boton antipanico a las:', 'Ella activó el botón antipanico en la siguiente ubicación:', 'alertaMail'),
(null, 'y longitud:', 'Alerta en latitud:', 'alertaTelegramP'),
(null, 'y longitud:', 'Alerta en latitud:', 'alertaWspP');

select * from configmensaje;