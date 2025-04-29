<?php
require_once('db_connect.php');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $nom = $_POST['nom'];
    $specialite = $_POST['specialite'];
    $numero = $_POST['numero'];

    $sql = "INSERT INTO medecins (nom, specialite, numero) 
            VALUES ('$nom', '$specialite', '$numero')";

    if ($conn->query($sql) === TRUE) {
        echo "Médecin ajouté avec succès";
    } else {
        echo "Erreur : " . $conn->error;
    }
}
?>
