<?php


// $_POST = [
// 	'device_id' => 'ecGdZNta3EuH',
// 	'scan' => true,
// 	// 'scan' => null,
// 	// 'scan' => false,
// ];
if(($operation = DesktopFacade::getDatas('operation')) !== false){
	$reserve = DeviceFacade::getReserve();
	$hack = true;
	if($operation == 'ajout-employe'){
		$dektop_datas = DesktopFacade::getDatas();
		if($_GET['route'] == 'new_device' && (!isset($_POST['device_id']) || $_POST['device_id'] == null)){
			if($reserve){
				echo json_encode([
					'device_id' => null,
					'state' => 'wait-another'
				]);
			}
			else{
				
				$device_id = $dektop_datas['device_register'];

				DeviceFacade::setDeviceId($device_id);
				
				DeviceFacade::add(null, true);
				
				DeviceFacade::save();
				
				$hack = false;
				echo json_encode([
					'device_id' => $device_id,
					'state' => 'attempt-scan',
					'operation' => $operation
				]);
			}
		}
		else if(isset($_GET['route']) && $_GET['route'] == 'connexion'){
			if(isset($_POST['device_id'])){
				if(($reserve['device_id'] == $_POST['device_id']) == $dektop_datas['device_register']){
					echo json_encode([
						'device_id' => $_POST['device_id'],
						'state' => 'attempt-scan',
						'operation' => $operation
					]);
				}
				else echo json_encode([
					'device_id' => $_POST['device_id'],
					'state' => 'wait-another',
					'operation' => $operation,
				]);
				$hack = false;
			}
		}
		else if(isset($_GET['route']) && $_GET['route'] == 'authentification'){
			if(isset($_POST['device_id'])){
				if(($reserve['device_id'] == $_POST['device_id']) == $dektop_datas['device_register']){
					if(array_key_exists('scan', $_POST)){
						DeviceFacade::setDeviceId($reserve['device_id']);
						$state = 'wait-scan';
						if($_POST['scan'] === "true"){
							$state = 'authentified';
							DeviceFacade::authentified();
							DeviceFacade::save();
						}
						elseif($_POST['scan'] === "false"){
							$state = 'another-scan';
							DeviceFacade::setAuthentified(false);
							DeviceFacade::save();
						}
						else $state = 'another-scan';
						echo json_encode([
							'device_id' => $_POST['device_id'],
							'state' => $state,
							'operation' => $operation
						]);
						$hack = false; 
					}
				}
			}
		}
		else if(isset($_GET['route']) && $_GET['route'] == 'terminate'){
		}
	}
	else if($operation == 'identification-employe'){
		if($_GET['route'] == 'new_device' && (!isset($_POST['device_id']) || $_POST['device_id'] == null)){
			echo json_encode([
				// 'device_id' => null,
				// 'state' => 'connexion-not-new-device',
				'state' => 'connexion-not-new-device',
				'message' => 'Cet appareil n\'est pas enregistrer, l\'administrateur effectue en ce moment une IDENTIFICATION, veuillez attendre qu\'il finisse l\'operation et il vous enregistrera',
				'operation' => $operation
			]);
			$hack = false;
		}
		else if(isset($_GET['route']) && $_GET['route'] == 'connexion'){
			if($_POST['device_id']){
				if($reserve == false || ($reserve['device_id'] == $_POST['device_id'])){
					if($reserve == false){
						DeviceFacade::setDeviceId($_POST['device_id'])
							->add(null, true, null, true)
							->reserve()
							->save()
						;
					}
					echo json_encode([
						'device_id' => $_POST['device_id'],
						'state' => 'attempt-scan',
						'operation' => $operation
					]);
				}
				else echo json_encode([
					'device_id' => null,
					'state' => 'wait-another',
					'operation' => $operation
				]);
				$hack = false;
			}
		}
		else if(isset($_GET['route']) && $_GET['route'] == 'authentification'){
			if($_POST['device_id']){
				if($reserve != false && $reserve['device_id'] == $_POST['device_id']){
					DeviceFacade::setDeviceId($reserve['device_id']);
					if(isset($_POST['scan']) && $_POST['scan'] == "true"){
						DeviceFacade::authentified();
						echo json_encode([
							'device_id' => $reserve['device_id'],
							'state' => 'authentified',
							'operation' => $operation
						]);
					}
					else{
						DeviceFacade::setAuthentified(false);
						echo json_encode([
							'device_id' => $reserve['device_id'],
							'state' => 'not-authentified',
							'operation' => $operation
						]);
					}
					DeviceFacade::save();
					$hack = false;
				}
			}
		}
		else if(isset($_GET['route']) && $_GET['route'] == 'terminate'){
			if($_POST['device_id']){
				if($reserve != false && $reserve['device_id'] == $_POST['device_id']){
					DeviceFacade::setDeviceId($reserve['device_id']);
					if($reserve['identified'] === true){
						DeviceFacade::disabelReserve()->setIdentified(null)->setAuthentified(null);
						echo json_encode([
							'device_id' => $reserve['device_id'],
							'state' => 'system-found',
							'operation' => $operation
						]);
					}
					elseif($reserve['identified'] === false){
						DeviceFacade::removeReserve();
						echo json_encode([
							'device_id' => $reserve['device_id'],
							'state' => 'remove',
							'operation' => $operation
						]);
					}
					else echo json_encode([
						'device_id' => $reserve['device_id'],
						'state' => 'wait',
						'operation' => $operation
					]);
					DeviceFacade::save();
				}
				$hack = false;
			}

		}
	}
	if($hack) echo json_encode([
		'device_id' => null,
		'state' => 'hacking',
		'operation' => $operation
	]);
}
else echo json_encode([
	// 'device_id' => null,
	'state' => 'desktop-broke'
]);