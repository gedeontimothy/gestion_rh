<?php

DesktopFacade::setDatas('route', $_GET['route']);
	$reserve = DeviceFacade::getReserve();
	if(isset($_GET['operation']))
		DesktopFacade::setDatas('operation', $_GET['operation']);
	if($_GET['route'] == 'register'){
		// var_dump($_POST);
		DesktopFacade::setDatas('device_register', isset($_POST['device_id']) ? $_POST['device_id'] :  generate_device_id());
		if($reserve){
			DeviceFacade::setDeviceId($reserve['device_id'])->disabelReserve();
		}
	}
	elseif($_GET['route'] == 'register-attempt-scan'){
		if($reserve){
			echo "phone-connect"; // key : 0
			if($reserve){
				if($reserve['authentified'] === true) {
					echo "\n" . $reserve['device_id']; // device_id : 1
					DesktopFacade::truncate();
					DesktopFacade::save();
					DeviceFacade::setDeviceId($reserve['device_id'])->identified()->disabelReserve()->save();
				}
				elseif($reserve['authentified'] === false) echo "\nfalse"; // device_id : 1
				else echo "\nnull"; // device_id : 1
			}
		}
		else echo "no-phone-connect\nnull";// key : 0 |\n| device_id : 1
	}
	elseif($_GET['route'] == 'tentative-max-atteint'){
		if($reserve){
			DeviceFacade::setDeviceId($reserve['device_id'])->disabelReserve();
		}
	}
	elseif($_GET['route'] == 'identification') {
		if($reserve){
			DeviceFacade::setDeviceId($reserve['device_id'])->disabelReserve();
		}
		echo "ok";
	}
	elseif($_GET['route'] == 'identification-attempt-scan') {
		if($reserve){
			if($reserve['authentified'] === true){
				echo "authentified\n" . $reserve['device_id'];
				DesktopFacade::truncate();
				DeviceFacade::setDeviceId($reserve['device_id'])
					->identified()
					->disabelReserve()
					->save()
				;
			}
			elseif($reserve['authentified'] === false){
				echo "not-authentified" . "\n" . $reserve['device_id'];
				DesktopFacade::truncate();
				DeviceFacade::setDeviceId($reserve['device_id'])
					->setIdentified(false)
					->disabelReserve()
					->save()
				;
			}
			elseif($reserve['authentified'] === null){
				echo "attempt-scan" . "\n" . $reserve['device_id'];
			}
		}
		else echo "nothing-device\nnull";
	}
	elseif($_GET['route'] == 'identification-terminate') {
		DesktopFacade::truncate();
		if(is_array($reserve) && isset($reserve['device_id'])){
			if($reserve['device_id'] == $_POST['device_id']){
				DeviceFacade::setDeviceId($reserve['device_id']);
				DeviceFacade::identified()->save();
				echo "ok\nnull";
			}
			else echo "not-this-device\n".$reserve['device_id'];
		}
		echo "ok\nnull";
	}
	
	DesktopFacade::save();