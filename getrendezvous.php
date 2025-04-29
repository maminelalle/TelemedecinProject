<?php
require_once('db_connect.php');

$sql = "SELECT * FROM rendezvous";
$result = $conn->query($sql);

$rendezvous = array();
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $rendezvous[] = $row;
    }
    echo json_encode($rendezvous);
} else {
    echo "0 résultats";
}
?>
