<?php
session_start();
require_once 'global.php';

error_reporting(E_ERROR | E_PARSE);
header('Content-type: application/json; charset=utf-8');

header('Expires: Sun, 01 Jan 2014 00:00:00 GMT');
header('Cache-Control: no-store, no-cache, must-revalidate');
header('Cache-Control: post-check=0, pre-check=0', FALSE);
header('Pragma: no-cache');

$response = array();

$request = explode('/', trim($_SERVER['PATH_INFO'], '/'));
$action = strtolower(array_shift($request));
$id = array_shift($request);

require_once 'db_config.php';
$db = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

if ($db->connect_errno) {
    $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
    $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการเชื่อมต่อฐานข้อมูล';
    $response[KEY_ERROR_MESSAGE_MORE] = $db->connect_error;
    echo json_encode($response);
    exit();
}
$db->set_charset("utf8");

switch ($action) {
    case 'get_word':
        doGetWord();
        break;
    case 'get_sentence':
        doGetSentence();
        break;
    case 'get_slang':
        doGetSlang();
        break;
    default:
        $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
        $response[KEY_ERROR_MESSAGE] = 'No action specified or invalid action.';
        $response[KEY_ERROR_MESSAGE_MORE] = '';
        break;
}

$db->close();
echo json_encode($response);
exit();

function doGetWord() {
    global $db, $response;

    //food = อาหาร, appliance = ของใช้, greeting = การทักทาย, relative = เครือญาติ
    $categoryTitle['food'] = "อาหาร";
    $categoryTitle['appliance'] = "ของใช้";
    $categoryTitle['greeting'] = "การทักทาย";
    $categoryTitle['relative'] = "เครือญาติ";
    $categoryTitle['fruits/vegetables'] = "ผัก/ผลไม้";

    $sql = "SELECT DISTINCT category FROM thaitin_word";
    if ($result = $db->query($sql)) {
        $response[KEY_ERROR_CODE] = ERROR_CODE_SUCCESS;
        $response[KEY_ERROR_MESSAGE] = 'อ่านข้อมูลสำเร็จ';
        $response[KEY_ERROR_MESSAGE_MORE] = '';
        $response[KEY_DATA_LIST] = array();

        while ($row = $result->fetch_assoc()) {
            $category = array();
            $category['name'] = $row['category'];
            $category['title'] = $categoryTitle[$row['category']];
            $category['word_list'] = array();

            $sql = "SELECT word, translation, category FROM thaitin_word WHERE category = '{$category['name']}'";
            if ($wordResult = $db->query($sql)) {
                while ($wordRow = $wordResult->fetch_assoc()) {
                    $word = array();
                    $word['word'] = $wordRow['word'];
                    $word['translation'] = $wordRow['translation'];
                    $word['category'] = $wordRow['category'];

                    array_push($category['word_list'], $word);
                }
                $wordResult->close();

            } else {
                $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
                $response[KEY_ERROR_MESSAGE] = "เกิดข้อผิดพลาดในการอ่านข้อมูล [Category: {$category['name']}]";
                $errMessage = $db->error;
                $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $sql";
            }

            array_push($response[KEY_DATA_LIST], $category);
        }
        $result->close();

    } else {
        $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
        $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการอ่านข้อมูล (1)';
        $errMessage = $db->error;
        $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $sql";
    }
}

function doGetSentence() {
    global $db, $response;

    $sql = "SELECT sentence, translation FROM thaitin_sentence";
    if ($result = $db->query($sql)) {
        $response[KEY_ERROR_CODE] = ERROR_CODE_SUCCESS;
        $response[KEY_ERROR_MESSAGE] = 'อ่านข้อมูลสำเร็จ';
        $response[KEY_ERROR_MESSAGE_MORE] = '';
        $response[KEY_DATA_LIST] = array();

        while ($row = $result->fetch_assoc()) {
            $sentence = array();
            $sentence['sentence'] = $row['sentence'];
            $sentence['translation'] = $row['translation'];

            array_push($response[KEY_DATA_LIST], $sentence);
        }
        $result->close();

    } else {
        $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
        $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการอ่านข้อมูล';
        $errMessage = $db->error;
        $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $sql";
    }
}

function doGetSlang() {
    global $db, $response;

    $sql = "SELECT slang, translation FROM thaitin_slang";
    if ($result = $db->query($sql)) {
        $response[KEY_ERROR_CODE] = ERROR_CODE_SUCCESS;
        $response[KEY_ERROR_MESSAGE] = 'อ่านข้อมูลสำเร็จ';
        $response[KEY_ERROR_MESSAGE_MORE] = '';
        $response[KEY_DATA_LIST] = array();

        while ($row = $result->fetch_assoc()) {
            $slang = array();
            $slang['slang'] = $row['slang'];
            $slang['translation'] = $row['translation'];

            array_push($response[KEY_DATA_LIST], $slang);
        }
        $result->close();

    } else {
        $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
        $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการอ่านข้อมูล';
        $errMessage = $db->error;
        $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $sql";
    }
}

function doGetBag()
{
    global $db, $response;

    $sql = "SELECT * FROM `bagculate_bag`";
    if ($result = $db->query($sql)) {
        $response[KEY_ERROR_CODE] = ERROR_CODE_SUCCESS;
        $response[KEY_ERROR_MESSAGE] = 'อ่านข้อมูลสำเร็จ';
        $response[KEY_ERROR_MESSAGE_MORE] = '';

        $bagList = array();
        while ($row = $result->fetch_assoc()) {
            $bag = array();
            $bag['id'] = (int)$row['id'];
            $bag['name'] = $row['name'];
            $bag['type'] = (int)$row['type'];
            $bag['weight'] = floatval($row['weight']);
            array_push($bagList, $bag);
        }
        $result->close();
        $response[KEY_DATA_LIST] = $bagList;
    } else {
        $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
        $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการอ่านข้อมูล';
        $errMessage = $db->error;
        $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $sql";
    }
}

function doGetObject()
{
    global $db, $response;

    $sql = "SELECT * FROM `bagculate_object` ORDER BY type, name";
    if ($result = $db->query($sql)) {
        $response[KEY_ERROR_CODE] = ERROR_CODE_SUCCESS;
        $response[KEY_ERROR_MESSAGE] = 'อ่านข้อมูลสำเร็จ';
        $response[KEY_ERROR_MESSAGE_MORE] = '';

        $objectList = array();
        while ($row = $result->fetch_assoc()) {
            $object = array();
            $object['id'] = (int)$row['id'];
            $object['name'] = $row['name'];
            $object['type'] = $row['type'];
            $object['weight'] = floatval($row['weight']);
            array_push($objectList, $object);
        }
        $result->close();
        $response[KEY_DATA_LIST] = $objectList;
    } else {
        $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
        $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการอ่านข้อมูล';
        $errMessage = $db->error;
        $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $sql";
    }
}

function doGetHistory()
{
    global $db, $response;

    $userId = $db->real_escape_string($_POST['user_id']);

    $sql = "SELECT h.id, h.created_at, b.name, b.type, b.weight FROM bagculate_history h INNER JOIN bagculate_bag b ON h.bag_id = b.id WHERE user_id = $userId ORDER BY created_at DESC";
    if ($result = $db->query($sql)) {
        $historyList = array();
        while ($row = $result->fetch_assoc()) {
            $history = array();
            $historyId = (int)$row['id'];
            $history['id'] = $historyId;
            $history['created_at'] = $row['created_at'];
            $history['bag'] = array(
                'name' => $row['name'],
                'type' => (int)$row['type'],
                'weight' => floatval($row['weight'])
            );
            $history['object_list'] = array();

            $sql = "SELECT o.name, o.type, o.weight, hd.count FROM bagculate_history_details hd INNER JOIN bagculate_object o ON hd.object_id = o.id WHERE history_id = $historyId";
            if ($objectResult = $db->query($sql)) {
                $response[KEY_ERROR_CODE] = ERROR_CODE_SUCCESS;
                $response[KEY_ERROR_MESSAGE] = 'อ่านข้อมูลสำเร็จ';
                $response[KEY_ERROR_MESSAGE_MORE] = '';

                while ($objectRow = $objectResult->fetch_assoc()) {
                    $object = array();
                    $object['name'] = $objectRow['name'];
                    $object['type'] = $objectRow['type'];
                    $object['weight'] = floatval($objectRow['weight']);
                    $object['count'] = (int)$objectRow['count'];

                    array_push($history['object_list'], $object);
                }
                $objectResult->close();
            } else {
                $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
                $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการอ่านข้อมูล (2)';
                $errMessage = $db->error;
                $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $sql";
                return;
            }

            array_push($historyList, $history);
        }
        $result->close();
        $response[KEY_DATA_LIST] = $historyList;
    } else {
        $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
        $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการอ่านข้อมูล (1)';
        $errMessage = $db->error;
        $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $sql";
    }
}

function doAddHistory()
{
    global $db, $response;

    $userId = $db->real_escape_string($_POST['user_id']);
    $bagId = $db->real_escape_string($_POST['bag_id']);
    $objectList = $db->real_escape_string($_POST['object_list']);

    $sql = "INSERT INTO bagculate_history (user_id, bag_id) VALUES ($userId, $bagId)";
    if ($result = $db->query($sql)) {
        $insertId = $db->insert_id;

        $objectList = explode(',', $objectList);
        $valueList = "";
        foreach ($objectList as $object) {
            $objectPart = explode('-', $object);
            $objectId = $objectPart[0];
            $count = $objectPart[1];

            $valueList .= "($insertId, $objectId, $count),";
        }
        $valueList = substr($valueList, 0, -1);

        $insertObjectListSql = "INSERT INTO bagculate_history_details (history_id, object_id, count) 
                                VALUES $valueList";
        if ($db->query($insertObjectListSql)) {
            $response[KEY_ERROR_CODE] = ERROR_CODE_SUCCESS;
            $response[KEY_ERROR_MESSAGE] = 'บันทึกข้อมูลสำเร็จ';
            $response[KEY_ERROR_MESSAGE_MORE] = '';
        } else {
            $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
            $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการบันทึกข้อมูล (2)';
            $errMessage = $db->error;
            $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $insertObjectListSql";
        }
    } else {
        $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
        $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการบันทึกข้อมูล (1)';
        $errMessage = $db->error;
        $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $sql";
    }
}

?>
