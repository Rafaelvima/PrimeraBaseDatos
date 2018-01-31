<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<?php
require_once 'vendor/autoload.php';
$servername = "db4free.net:3307";
$username = "oscarnovillo";
$password = "c557ef";
$database = "clasesdaw";
try {
    $conn = "mysql:host=$servername;dbname=$database";
    $db = new PDO($conn, $username, $password);
} catch (PDOException $e) {
    echo $e->getMessage();
}


$alumnos = $db->prepare("SELECT * FROM ALUMNOS");
$alumnos->setFetchMode(PDO::FETCH_ASSOC);
$alumnos->execute();

$asignaturas = $db->prepare("SELECT * FROM ASIGNATURAS");
$asignaturas->setFetchMode(PDO::FETCH_ASSOC);
$asignaturas->execute();

if (isset($_REQUEST['op'])) {
    $op = $_REQUEST['op'];
} else {
    $op = null;
}
if (isset($_REQUEST['id_alumno'])) {
    $id_alumno = $_REQUEST['id_alumno'];
} else {
    $id_alumno = null;
}
if (isset($_REQUEST['id_asignatura'])) {
    $id_asignatura = $_REQUEST['id_asignatura'];
} else {
    $id_asignatura = null;
}
if (isset($_REQUEST['nota'])) {
    $nota = $_REQUEST['nota'];
} else {
    $nota = null;
}

switch ($op) {
    case "ver":
        $stmt = $db->prepare("SELECT NOTA FROM NOTAS WHERE ID_ALUMNO = ? AND ID_ASIGNATURA = ?");
        $stmt->bindParam(1, $id_alumno);
        $stmt->bindParam(2, $id_asignatura);
        $stmt->execute();
        if (!$fila = $stmt->fetch(PDO::FETCH_ASSOC)) {
            $nota = null;
        } else {
            $nota = $fila['NOTA'];
        }
        break;
    case "guardar":

        $stmt = $db->prepare("UPDATE NOTAS SET NOTA = ? WHERE ID_ALUMNO = ? AND ID_ASIGNATURA = ?");
        $stmt->bindParam(1, $nota);
        $stmt->bindParam(2, $id_alumno);
        $stmt->bindParam(3, $id_asignatura);
        $stmt->execute();
        if (!($stmt->rowCount()) > 0) {
            $stmt = $db->prepare("INSERT INTO NOTAS (ID_ALUMNO,ID_ASIGNATURA,NOTA) VALUES (?,?,?)");
            $stmt->bindParam(1, $id_alumno);
            $stmt->bindParam(2, $id_asignatura);
            $stmt->bindParam(3, $nota);
            $stmt->execute();
        }
        break;
    case "borrar":
        $stmt = $db->prepare("DELETE FROM NOTAS WHERE ID_ALUMNO = ? AND ID_ASIGNATURA = ?");
        $stmt->bindParam(1, $id_alumno);
        $stmt->bindParam(2, $id_asignatura);
        $stmt->execute();
        break;
}
if ($op != "ver") {
    if (($filas = $stmt->rowCount()) > 0) {
        echo $filas . " filas modificadas";
    } else {
        echo "No modificaciones";
    }
}
$stmt = null;

$db = null;
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <script>
            function cargarAlumno(id) {
                document.getElementById("id_alumno").value = id;
            }
            function cargarAsignatura(id) {
                document.getElementById("id_asignatura").value = id;
            }
            function guardar() {
                document.getElementById("op").value = "guardar";
            }
            function borrar() {
                document.getElementById("op").value = "borrar";
            }
            function cargarNota() {
                document.getElementById("op").value = "ver";
            }

        </script>
    </head>
    <body>
        <h1>ALUMNOS</h1>
        <table border="1">
            <tr>
                <td>
                    <select id="alumno" onchange="cargarAlumno(this.value, this.options[this.selectedIndex].innerHTML)">
                        <option disabled selected>Selecciona un alumno</option>
                        <?php foreach ($alumnos as $alumno) { ?>
                            <option value="<?php echo $alumno['ID'] ?>" name="<?php echo $alumno['NOMBRE'] ?>"><?php echo $alumno['NOMBRE'] ?></option>
                        <?php } ?>
                    </select>
                </td>

            </tr>
        </table>
        <span>Asignatura: </span>
        <table border="1">
            <tr>
                <td>
                    <select id="asignatura" onchange="cargarAsignatura(this.value, this.options[this.selectedIndex].innerHTML)">
                        <option disabled selected>Selecciona una asignatura</option>
                        <?php foreach ($asignaturas as $asignatura) { ?>
                            <option value="<?php echo $asignatura['id'] ?>"><?php echo $asignatura['NOMBRE'] ?></option>
                        <?php } ?>
                    </select>
                </td>

            </tr> 

        </table>
        <form action ="notas.php?op=" method="get">
            <input type="hidden" id="nombre" name="nombre" size="12"/>
            id_alumno: <input type="text" id="id_alumno" name="id_alumno" value="<?php echo $id_alumno ?>" size="12"/>
            id_asignatura: <input type="text" id="id_asignatura" name="id_asignatura" value="<?php echo $id_asignatura ?>" s="12"/>
            nota: <input type="text" id="nota" name="nota" value="<?php echo $nota ?>" >

            <input type="hidden" id="op" name="op"/>
            <button value="seleccionar" onclick="cargarNota();"> cargar</button>
            <button value="update" onclick="guardar();"> guardar</button>
            <button value="delete" onclick="borrar();"> delete  </button>
        </form>
    </body>
</html>
