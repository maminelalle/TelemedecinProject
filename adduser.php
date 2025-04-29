<?php
// Fichier de connexion à la base de données
require_once('db_connect.php');

// Vérifier si les données sont envoyées via POST
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    // Récupérer les valeurs envoyées via POST
    $nom = $_POST['nom'];
    $numero = $_POST['numero']; // Numéro de téléphone
    $password = $_POST['password'];

    // Valider les données (ajoute plus de validation selon tes besoins)
    if (!empty($nom) && !empty($numero) && !empty($password)) {
        // Hasher le mot de passe avant de le stocker
        $hashedPassword = password_hash($password, PASSWORD_DEFAULT);

        // Insérer les données dans la base
        $sql = "INSERT INTO users (nom, numero, password) VALUES (?, ?, ?)";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("sss", $nom, $numero, $hashedPassword);

        // Exécuter la requête
        if ($stmt->execute()) {
            echo json_encode(['message' => 'Inscription réussie']);
        } else {
            echo json_encode(['message' => 'Erreur lors de l\'inscription']);
        }
    } else {
        echo json_encode(['message' => 'Tous les champs sont obligatoires']);
    }
}
?>
