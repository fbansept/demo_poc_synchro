INSERT INTO `utilisateur` (`id`, `mot_de_passe`, `pseudo`) VALUES
(1, '$2a$10$uz5dB8kKtjb37GwBLXUtEeALDOq4Ge/DHx2CmXWOf.hd1ave7Al0i', 'franck'),
(2, '$2a$10$uz5dB8kKtjb37GwBLXUtEeALDOq4Ge/DHx2CmXWOf.hd1ave7Al0i', 'john'),
(3, '$2a$10$uz5dB8kKtjb37GwBLXUtEeALDOq4Ge/DHx2CmXWOf.hd1ave7Al0i', 'toto');

INSERT INTO `role` (`id`, `denomination`) VALUES
(1, 'ROLE_UTILISATEUR'),
(2, 'ROLE_ADMINISTRATEUR');

INSERT INTO `utilisateur_role` (`utilisateur_id`, `role_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(3, 2);

INSERT INTO `incident` (`id`, `code`, `texte`) VALUES (NULL, '123', 'incident 1');