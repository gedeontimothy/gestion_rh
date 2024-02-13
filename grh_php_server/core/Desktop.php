<?php
require_once __DIR__ . '/Crossment.php';

class Desktop extends Crossment{
	
	protected static $i;
	
	public function getFile(){
		return ROOT . '/datas/desktop.php';
	}


}