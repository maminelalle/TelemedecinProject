<?php
// getuser.php

$servername = "localhost";
$username = "root"; // Nom d'utilisateur de votre base de données
$password = ""; // Mot de passe pour l'utilisateur
$dbname = "telemedecin"; // Nom de la base de données

// Créer la connexion
$conn = new mysqli($servername, $username, $password, $dbname);

// Vérifier la connexion
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$numero = $_GET['numero']; // Récupérer le téléphone passé en paramètre
$password = $_GET['password']; // Récupérer le mot de passe passé en paramètre

// Requête SQL pour vérifier si l'utilisateur existe
$sql = "SELECT * FROM users WHERE numero = '$numero' AND password = '$password'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // Utilisateur trouvé, connexion réussie
    $response = array('success' => 1);
} else {
    // Utilisateur non trouvé
    $response = array('success' => 0);
}

$conn->close();

// Retourner la réponse en format JSON
echo json_encode($response);
?>
