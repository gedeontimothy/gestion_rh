<?php
require_once __DIR__ . '/Crossment.php';

class Device extends Crossment{
	
	protected static $i; 
	
	public $device_id;
	
	public function getFile(){
		return ROOT . '/datas/device_mobile.php';
	}

	public function setDeviceId(string $device_id){
		$this->device_id = $device_id;
		return $this;
	}

	public function add($authentified = null, $reserve = false, $identified = null, $force = false){
		if(!is_null($this->device_id) && (!$this->deviceExists($this->device_id) || $force === true)){
			$this->datas[$this->device_id] = [
				'identified' => $identified,
				'authentified' => $authentified,
				'reserve' => $reserve
			];
		}
		return $this;
	}

	public function deviceExists(){
		return array_key_exists($this->device_id, $this->datas);
	}

	public function identified(){
		$this->datas[$this->device_id]['identified'] = true;
		return $this;
	}

	public function reserve(){
		$this->datas[$this->device_id]['reserve'] = true;
		return $this;
	}

	public function disabelReserve(){
		$this->datas[$this->device_id]['reserve'] = false;
		return $this;
	}
	public function disabelAnyReserve(){
		if($this->getReserve('device_id'))
			$this->datas[$this->getReserve('device_id')] = false;
		return $this;
	}

	public function authentified(){
		$this->datas[$this->device_id]['authentified'] = true;
		return $this;
	}

	public function setReserve($state){
		$this->datas[$this->device_id]['reserve'] = $state;
		return $this;
	}

	public function setAuthentified($state){
		$this->datas[$this->device_id]['authentified'] = $state;
		return $this;
	}
	public function setIdentified($state){
		$this->datas[$this->device_id]['identified'] = $state;
		return $this;
	}

	public function reserveExist(){
		return $this->getReserve() !== false;
	}
	public function getReserve($key = null){
		if(!empty($this->datas)){
			foreach ($this->datas as $device_id => $value)
				if($value['reserve']){
					$datas = array_merge($value, ["device_id" => $device_id]);
					return !is_null($key) ? (array_key_exists($key, $datas) ? $datas[$key] : false) : $datas;
				}
		}
		return false;
	}

	public function removeReserve(){
		$this->remove($this->device_id);
		return $this;
	}

}