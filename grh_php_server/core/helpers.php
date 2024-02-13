<?php
function dd(...$args){
	if(isset($_SERVER)) echo "<pre>";
	var_dump(...$args);
	// echo implode("\n\n", array_map(fn ($a) => var_export($a, true), $args));
	if(isset($_SERVER)) echo "</pre>";
}

function generate_device_id(){
	return substr(str_shuffle('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0987654321'), 0, 12);
}

// function add_device_mobile($device_id, $authentified = null){
// 	global $files;
// 	$datas = require $files['device_mobile'];
// 	file_put_contents($files['device_mobile'], "<?php return \n" . var_export($datas, true) . ';');
// }
// function reserve_device_mobile($device_id, $authentified = null){
// 	global $files;
// 	$datas = require $files['device_mobile'];
// 	file_put_contents($files['device_mobile'], "<?php return \n" . var_export($datas, true) . ';');
// }