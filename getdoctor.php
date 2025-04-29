<?php
require_once('db_connect.php');

$sql = "SELECT * FROM medecins";
$result = $conn->query($sql);

$doctors = array();
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $doctors[] = $row;
    }
    echo json_encode($doctors);
} else {
    echo "0 résultats";
}
?>
