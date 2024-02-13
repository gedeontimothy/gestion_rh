<?php
require_once ROOT . '/core/Desktop.php';
require_once __DIR__ . '/Facade.php';

class DesktopFacade extends Facade {

	public static function i(){
		return Desktop::getInstance();
	}

}