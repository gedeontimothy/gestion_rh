<?php
require_once ROOT . '/core/Device.php';
require_once __DIR__ . '/Facade.php';

class DeviceFacade extends Facade {

	public static function i(){
		return Device::getInstance();
	}

}