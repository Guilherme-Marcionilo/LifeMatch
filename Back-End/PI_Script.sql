CREATE TABLE `Postagem` (
	`id_postagem` INT NOT NULL AUTO_INCREMENT,
	`descricao` TEXT NOT NULL,
	`fk_tema` INT NOT NULL AUTO_INCREMENT,
	`data_conclusao` DATE NOT NULL,
	`data_postagem` DATE NOT NULL,
	`fk_usuarios` INT NOT NULL,
	`imagens_postagem` VARCHAR(255) NOT NULL,
	`identificacao` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id_postagem`)
);

CREATE TABLE `Tema` (
	`id_tema` INT NOT NULL AUTO_INCREMENT,
	`categoria_ajuda` varchar(20) NOT NULL,
	`descricao` varchar(255) NOT NULL,
	PRIMARY KEY (`id_tema`)
);

CREATE TABLE `Usuario` (
	`id_usuario` INT NOT NULL AUTO_INCREMENT,
	`nome_completo` varchar(50) NOT NULL,
	`senha` varchar(10) NOT NULL UNIQUE,
	`categoria_usuario` BOOLEAN NOT NULL,
	`email` varchar(50) NOT NULL,
	`imagens_usuario` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id_usuario`)
);

ALTER TABLE `Postagem` ADD CONSTRAINT `Postagem_fk0` FOREIGN KEY (`fk_tema`) REFERENCES `Tema`(`id_tema`);

ALTER TABLE `Postagem` ADD CONSTRAINT `Postagem_fk1` FOREIGN KEY (`fk_usuarios`) REFERENCES `Usuario`(`id_usuario`);

