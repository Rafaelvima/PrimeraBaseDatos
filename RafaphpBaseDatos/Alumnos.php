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

// Create connection
$conn = new mysqli($servername, $username, $password, $database);

// Check connection
$db = new MysqliDb($servername, $username, $password, $database);
if (isset($_REQUEST['op'])) {
    $op = $_REQUEST['op'];
} else {
    $op = null;
}
if (isset($_REQUEST['id'])) {
    $idalu = $_REQUEST['id'];
} else {
    $idalu = null;
}
if (isset($_REQUEST['nombre'])) {
    $nombrealu = $_REQUEST['nombre'];
} else {
    $nombrealu = null;
}
if (isset($_REQUEST['fecha'])) {
    $format = new DateTime($_REQUEST['fecha']);
    $fechaalu = $format->format('Y-m-d');
} else {
    $fechaalu = null;
}
if (isset($_REQUEST['mayor'])) {
    if (empty($_REQUEST['mayor'])) {
        $mayoralu = 0;
    } elseif ($_REQUEST['mayor'] == "on") {
        $mayoralu = 1;
    } else {
        $mayoralu = 0;
    }
} else {
    $mayoralu = 0;
}
switch ($op) {
    case "insert":
        $data = Array(
            'NOMBRE' => $nombrealu,
            'FECHA_NACIMIENTO' => $format,
            'MAYOR_EDAD' => $mayoralu
        );

        $id = $db->insert('ALUMNOS', $data);
        if ($id) {
            echo 'user was created. Id=' . $id;
        } else {
            echo 'insert failed: ' . $db->getLastError();
        }
        break;
    case "delete":
        $db->where('ID', $idalu);
        if ($db->delete('ALUMNOS'))
            echo 'successfully deleted';
        break;
    case "update":
        $data = Array(
            'NOMBRE' => $nombrealu,
            'FECHA_NACIMIENTO' => $fechaalu,
            'MAYOR_EDAD' => $mayoralu,
        );
        $db->where('ID', $idalu);
        if ($db->update('ALUMNOS', $data))
            echo $db->count . ' filas actualizadas';
        else
            echo 'update failed: ' . $db->getLastError();
        break;
}

$alumnos = $db->get('ALUMNOS');
//insertar
?>


<?php
$db->disconnect();
?>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>

            function cargarAlumno(id, nombre, fecha_nacimiento, mayor) {
                document.getElementById("nombre").value = nombre;
                document.getElementById("idasignatura").value = id;
                document.getElementById("fecha").value = fecha_nacimiento;
                document.getElementById("mayor").checked = mayor;
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

        <table border = "1">
            <?php
            foreach ($alumnos as $alumno) {
                ?>
                <tr>
                    <td><input type="button" value="cargar <?php echo $alumno['ID'] ?>" 
                               onclick="cargarAlumno('<?php echo $alumno['ID'] ?>',
                                               '<?php echo $alumno['NOMBRE'] ?>',
                                               ' <?php echo $alumno['FECHA_NACIMIENTO'] ?>',
                                               '<?php if ($alumno['MAYOR_EDAD'] == 1) echo 'checked' ?>')"/>
                    </td>
                    <td>
                        <?php echo $alumno['NOMBRE']; ?>
                    </td>

                    <td>
                        <?php echo $alumno['FECHA_NACIMIENTO']; ?>
                    </td>
                    <td>
                        <?php ?><input type="checkbox"  <?php if ($alumno['MAYOR_EDAD'] == 1) echo 'checked' ?>/> 
                    </td>
                </tr>


            <?php } ?> <?php
          
            echo "FIN";
            ?>
            <!-- -->

        </table>
        <form action ="alumnos.php?op=" method="get">
            <input type="hidden" id="idasignatura" name="id" />
            <input type="text" id="nombre" name="nombre" size="12"/>
            <input type="text" id="fecha" name="fecha" size="12"/>
            <input type="checkbox"  name="mayor" id="mayor"/>
            <input type="hidden" id="op" name="op" value=''/>
            <button value="update" onclick="updForm();"> actualizar</button>
            <button value="delete" onclick="delForm();"> delete</button>
            <button value="insertarAlumno" onclick="insForm();"> insertar</button>
        </form>
    </body>
</html>
