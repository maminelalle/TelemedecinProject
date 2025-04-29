<?php
require_once('db_connect.php');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $date = $_POST['date'];
    $heure = $_POST['heure'];
    $motif = $_POST['motif'];
    $doctor = $_POST['doctor'];
    $user_id = $_POST['user_id'];

    $sql = "INSERT INTO rendezvous (date, heure, motif, user_id, medecin_id) 
            VALUES ('$date', '$heure', '$motif','$user_id', '$medecin_id')";

    if ($conn->query($sql) === TRUE) {
        echo "Rendez-vous ajouté avec succès";
    } else {
        echo "Erreur : " . $conn->error;
    }
}
?>
