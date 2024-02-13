<?php

class Crossment {
	
	protected $datas;

	protected $original_datas;

	public function __construct(){

		$this->original_datas = $this->datas = require $this->getFile();
	}

	public function getFile(){
		throw new Exception("La classe " . static::class . " n'a pas de méthode 'getFile'. Veuillez la créer", 1);
	}

	public static function getInstance(...$args){
		if(is_null(static::$i))
			static::$i = new (static::class)(...$args);
		return static::$i;
	}

	public function addDatas(string $key, $value){
		if(!$this->datasExists($key)){
			$this->datas[$key] = $value;
			return true;
		}
		return false;
	}
	
	public function setDatas(string $key, $value){
		$this->datas[$key] = $value;
	}

	public function datasExists(string $key){
		return array_key_exists($key, $this->datas);
	}

	public function getDatas(string $key = null){
		return !is_null($key) 
			? ($this->datasExists($key)
				? $this->datas[$key] 
				: false
			) 
			: $this->datas
		;
	}

	public function remove($key){
		if(!$this->datasExists($key)){
			unset($this->datas[$key]);
			echo "string";
			return true;
		}
		return false;
	}

	public function save(){
		if($this->original_datas != $this->datas)
			file_put_contents($this->getFile(), "<?php return \n" . var_export($this->datas, true) . ';');
	}

	public function truncate(){
		$this->datas = [];
	}
}
