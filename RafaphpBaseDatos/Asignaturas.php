<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<?php
$servername = "db4free.net:3307";
$username = "oscarnovillo";
$password = "c557ef";
$database = "clasesdaw";

// Create connection
$conn = new mysqli($servername, $username, $password, $database);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
if (isset($_REQUEST['op'])) {
    $op = $_REQUEST['op'];
} else {
    $op = null;
}
if (isset($_REQUEST['idasignatura'])) {
    $idasig = $_REQUEST['idasignatura'];
} else {
    $idasig = null;
}
if (isset($_REQUEST['nombre'])) {
    $nombreasig = $_REQUEST['nombre'];
} else {
    $nombreasig = null;
}
if (isset($_REQUEST['curso'])) {
    $cursoasig = ($_REQUEST['curso']);
} else {
    $cursoasig = null;
}
if (isset($_REQUEST['ciclo'])) {
    $cicloasig = ($_REQUEST['ciclo']);
} else {
    $cicloasig = null;
}
switch ($op) {
    case "insert":
        $stmt = $conn->prepare("INSERT INTO ASIGNATURAS (NOMBRE, CURSO, CICLO)"
                . "VALUES(?,?,?)");
        $stmt->bind_param("sss", $nombreasig, $cursoasig, $cicloasig);
        $stmt->execute();

        /**/
        if ($stmt->affected_rows > 0) {
            echo "New record created successfully";
        } else {
            echo "Error: " . $sql . "<br>" . $conn->error;
        }
        echo $conn->insert_id;
        break;
    case "update":
        $stmt = $conn->prepare("UPDATE ASIGNATURAS SET NOMBRE=?, CURSO=?, CICLO=? WHERE ID=?");
        $stmt->bind_param("sssi", $nombreasig, $cursoasig, $cicloasig, $idasig);
        $stmt->execute();

        if (($stmt->affected_rows > 0)) {
            echo "Record updated successfully";
        } else {
            echo "Error updating record: ";
        }
        break;
    case "delete":
        // sql to delete a record
        $stmt = $conn->prepare("DELETE FROM ASIGNATURAS WHERE id=?");
        $stmt->bind_param("i", $idasig);
        $stmt->execute();

        if (($stmt->affected_rows > 0)) {
            echo "Record deleted successfully";
        } else {
            echo "Error deleting record: " . $conn->error;
        }
        break;
        $sql = "SELECT * FROM ASIGNATURAS";

        if (!$result = $conn->query($sql)) {
            die('There was an error running the query [' . $conn->error . ']');
        }
        echo 'Total results: ' . $result->num_rows;
}
$sql = "SELECT * FROM `ASIGNATURAS`";
if (!$result = $conn->query($sql)) {
    die('There was an error running the query [' . $conn->error . ']');
}
echo 'Total results: ' . $result->num_rows;
?>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>

            function cargarAsignatura(id, nombre, curso, ciclo) {
                document.getElementById("nombre").value = nombre;
                document.getElementById("idasignatura").value = id;
                document.getElementById("curso").value = curso;
                document.getElementById("ciclo").value = ciclo;

            }
        </script>
        <script>
            function updForm() {
                document.getElementById("op").value = "update";
            }
            function delForm() {
                document.getElementById("op").value = "delete";
            }
            function insForm() {
                document.getElementById("op").value = "insert";
            }
        </script>

    </head>
    <body>

        <h1>ASIGNATURAS</h1>
        <table border="1">
            <?php
            while ($row = $result->fetch_assoc()) {
                ?>
                <tr>
                    <td>
                        <input type="button" value="cargar <?php echo $row['id'] ?>" 
                               onclick="cargarAsignatura('<?php echo $row['id'] ?>',
                                                   '<?php echo $row['NOMBRE'] ?>',
                                                   ' <?php echo $row['CURSO'] ?>',
                                                   '<?php echo $row['CICLO'] ?>')"/>
                    </td> 
                    <td>
                        <?php echo $row['NOMBRE']; ?>
                    </td>

                    <td>
                        <?php echo $row['CURSO']; ?>
                    </td>
                    <td>
                        <?php echo $row['CICLO']; ?>
                    </td>
                </tr>


            <?php } ?>


            <!-- -->

        </table>
        <form action ="asignaturas.php?op=" method="get">
            <input type="hidden" id="idasignatura" name="idasignatura" />
            <input type="text" id="nombre" name="nombre" size="12"/>
            <input type="text" id="curso" name="curso" size="12"/>
            <input type="text"  name="ciclo" id="ciclo"/>
            <input type="hidden" id="op" name="op"/>
            <button value="update" onclick="updForm();"> actualizar</button>
            <button value="delete" onclick="delForm();"> delete</button>
            <button value="insert" onclick="insForm();"> insertar</button>
        </form>

    </body>
</html>
