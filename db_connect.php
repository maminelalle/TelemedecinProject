<?php
// db_connect.php : fichier de connexion à la base de données MySQL

$servername = "localhost"; // Adresse du serveur MySQL
$username = "root";        // Nom d'utilisateur MySQL
$password = "";            // Mot de passe MySQL
$dbname = "telemedecin";   // Nom de la base de données

// Créer la connexion
$conn = new mysqli($servername, $username, $password, $dbname);

// Vérifier la connexion
if ($conn->connect_error) {
    die("Connexion échouée: " . $conn->connect_error);
}
?>
