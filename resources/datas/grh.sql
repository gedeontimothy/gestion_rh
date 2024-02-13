-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 13 fév. 2024 à 02:13
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `grh`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(6) NOT NULL,
  `user` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id_admin`, `user`, `password`) VALUES
(5, 'admin', '1234'),
(6, 'QSDD', 'CSQCS'),
(7, 'CQDC', ' ZFRF'),
(8, 'F FKLZE FZE', ' ZFRF'),
(9, 'jovial', ''),
(10, 'SYLVA', 'AZERTY'),
(11, 'FVFSFZ', 'DNVKDNDV'),
(12, 'DAVID', 'AZERTY'),
(13, 'AZETHSDHDD', 'SDD'),
(14, '8 JOVIAL', 'aaaaaa'),
(15, '24 KALALA', '2000');

-- --------------------------------------------------------

--
-- Structure de la table `employes`
--

CREATE TABLE `employes` (
  `id_employe` int(11) NOT NULL,
  `empreinte` varchar(100) NOT NULL,
  `nom` varchar(250) NOT NULL,
  `post_nom` varchar(250) NOT NULL,
  `prenom` varchar(250) NOT NULL,
  `date_embauche` date NOT NULL,
  `photo` varchar(250) NOT NULL,
  `salire` float NOT NULL,
  `role_servise` varchar(250) NOT NULL,
  `statut` varchar(30) NOT NULL DEFAULT 'actif',
  `id_service` int(11) NOT NULL,
  `id_admin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `employes`
--

INSERT INTO `employes` (`id_employe`, `empreinte`, `nom`, `post_nom`, `prenom`, `date_embauche`, `photo`, `salire`, `role_servise`, `statut`, `id_service`, `id_admin`) VALUES
(7, 'déz', 'dz', 'dza', 'd', '2024-01-02', 'xQ', 20000.5, 'XQq', '<DA', 1, 5),
(8, 'HCBHCQ', 'JOVIAL', 'EFEA', 'VS', '2024-01-01', 'QCFZ', 20000.5, 'FZEGFZE', 'FZEFE', 1, 5),
(9, 'HCBHCQ', 'JOVIAL', 'EFEA', 'VS', '2024-01-01', 'QCFZ', 20000.5, 'FZEGFZE', 'FZEFE', 1, 5),
(10, 'HCBHCQ', 'JOVIAL', 'EFEA', 'VS', '2024-01-01', 'QCFZ', 20000.5, 'FZEGFZE', 'FZEFE', 1, 5),
(11, 'HCBHCQ', 'JOVIAL', 'EFEA', 'VS', '2024-01-01', 'QCFZ', 20000.5, 'FZEGFZE', 'FZEFE', 1, 5),
(16, 'null', ' j b', 'ebjc', ' jc', '2024-01-05', 'null', 66, 'CHRFC', 'Present', 1, 5),
(17, 'null', 'JVCJ', 'VKEROVK', 'ZERKFZE', '2024-01-19', 'null', 444, 'JJJ', 'Present', 1, 5),
(18, 'null', 'LEBANGE', 'SAMBA', 'ISMAEL', '2024-02-09', 'null', 1203000, 'SIMPLE EMPLOYE', 'Present', 1, 6),
(19, 'null', 'david', 'fsf', 'oeoe', '2024-02-16', 'null', 55555, 'SCQSDC', 'Present', 1, 6),
(20, 'null', 'david', 'fsf', 'oeoe', '2024-02-16', 'null', 55555, 'SCQSDC', 'Present', 1, 6),
(21, 'null', 'david', 'fsf', 'oeoe', '2024-02-16', 'null', 55555, 'SCQSDC', 'Present', 1, 6),
(23, 'null', 'KALALA', 'DADA', 'JUNIOR', '2024-02-15', 'null', 1300, 'SIMPLE', 'actif', 2, 10),
(24, 'null', 'KALALA', 'DADA', 'JUNIOR', '2024-02-15', 'null', 1300, 'SIMPLE', 'actif', 2, 10),
(25, 'null', 'JOWAN', 'DJOGO', 'WISSA', '2024-02-08', 'null', 1200, 'simple employe', 'actif', 3, 9),
(27, 'null', 'LLLLLLLLL', 'SSSSSSSS', 'KKKKKKKKKK', '2024-02-16', 'null', 1230000, 'XXXXXXXXX', 'actif', 2, 10),
(30, 'null', ' String name', ' String name', ' String name', '2024-02-01', 'IMG_20230707_124125.jpg', 11111, ' String name', 'actif', 1, 9),
(31, 'null', ' String name', ' String name', ' String name', '2024-02-01', 'IMG_20230707_124125.jpg', 11111, ' String name', 'actif', 1, 9),
(32, 'null', ' String name', ' String name', ' String name', '2024-02-01', 'IMG_20230707_124125.jpg', 11111, ' String name', 'actif', 1, 9),
(33, 'null', ' String name', ' String name', ' String name', '2024-02-01', 'IMG_20230707_124125.jpg', 11111, ' String name', 'actif', 1, 9),
(34, 'null', 'gggdefaultimage.png', 'defaultimage.png', 'defaultimage.png', '2024-02-02', 'null', 222, 'defaultimage.png', 'actif', 1, 9),
(35, 'null', 'dddd', 'ffff', 'fffff', '2024-02-08', 'defaultimage.png', 222, 'DFFFF', 'actif', 1, 9),
(36, 'null', 'IF', 'ffff', 'fffff', '2024-02-08', 'IMG_20230714_212716.jpg', 222, 'DFFFF', 'actif', 1, 9),
(37, 'null', 'DADAD', 'SALAM', 'DINOD', '2024-02-16', 'IMG_20231211_134510.jpg', 223000, 'RIEN', 'actif', 1, 10),
(38, 'null', 'GEDEON', 'TYMOTHY', 'AZERTYU', '2024-02-02', 'IMG_20231114_234754.jpg', 1, 'RIEN', 'actif', 1, 9),
(39, 'null', 'MUTOMBO', 'KABI', 'SYLVANO', '2024-02-09', 'msi.jpg', 23000, 'simple employé', 'actif', 1, 10),
(41, 'HCBHCQ', 'HCBHCQ', 'HCBHCQ', 'HCBHCQ', '2024-02-09', 'defaultimage.png', 1, 'HCBHCQ', 'actif', 1, 9),
(42, 'HCBHCQ', 'HCBHCQ', 'HCBHCQ', 'HCBHCQ', '2024-02-09', 'defaultimage.png', 1, 'HCBHCQ', 'actif', 1, 9),
(43, 'kqEKbdMWfQaD', 'Pola', 'Gula', 'Milo', '2024-02-11', 'defaultimage.png', 1290, 'Flemmard', 'actif', 2, 5);

-- --------------------------------------------------------

--
-- Structure de la table `encien_employe`
--

CREATE TABLE `encien_employe` (
  `code` int(11) NOT NULL,
  `date_demission` date NOT NULL,
  `cause` varchar(250) NOT NULL,
  `id_employe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `presence`
--

CREATE TABLE `presence` (
  `id_presence` int(11) NOT NULL,
  `heur_arrive` time NOT NULL,
  `heur_depart` time NOT NULL,
  `date` date NOT NULL,
  `id_employe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `presence`
--

INSERT INTO `presence` (`id_presence`, `heur_arrive`, `heur_depart`, `date`, `id_employe`) VALUES
(1, '00:00:12', '00:01:12', '2011-01-24', 7),
(2, '10:35:26', '00:00:00', '2024-02-09', 42);

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

CREATE TABLE `service` (
  `id_service` int(11) NOT NULL,
  `nom` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `service`
--

INSERT INTO `service` (`id_service`, `nom`) VALUES
(1, 'Acceuil'),
(2, 'PAIEMENT'),
(3, 'DSKDJSKDJS');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Index pour la table `employes`
--
ALTER TABLE `employes`
  ADD PRIMARY KEY (`id_employe`),
  ADD KEY `id_service` (`id_service`),
  ADD KEY `id_admin` (`id_admin`);

--
-- Index pour la table `encien_employe`
--
ALTER TABLE `encien_employe`
  ADD PRIMARY KEY (`code`),
  ADD KEY `id_employe` (`id_employe`);

--
-- Index pour la table `presence`
--
ALTER TABLE `presence`
  ADD PRIMARY KEY (`id_presence`),
  ADD KEY `id_employe` (`id_employe`);

--
-- Index pour la table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id_service`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `employes`
--
ALTER TABLE `employes`
  MODIFY `id_employe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT pour la table `encien_employe`
--
ALTER TABLE `encien_employe`
  MODIFY `code` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `presence`
--
ALTER TABLE `presence`
  MODIFY `id_presence` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `service`
--
ALTER TABLE `service`
  MODIFY `id_service` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `employes`
--
ALTER TABLE `employes`
  ADD CONSTRAINT `employes_ibfk_1` FOREIGN KEY (`id_service`) REFERENCES `service` (`id_service`),
  ADD CONSTRAINT `employes_ibfk_2` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`);

--
-- Contraintes pour la table `encien_employe`
--
ALTER TABLE `encien_employe`
  ADD CONSTRAINT `encien_employe_ibfk_1` FOREIGN KEY (`id_employe`) REFERENCES `employes` (`id_employe`);

--
-- Contraintes pour la table `presence`
--
ALTER TABLE `presence`
  ADD CONSTRAINT `presence_ibfk_1` FOREIGN KEY (`id_employe`) REFERENCES `employes` (`id_employe`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
