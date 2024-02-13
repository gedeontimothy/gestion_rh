<?php
class Facade {

	public static function __callStatic($method, $args){
		return method_exists(static::i(), $method) 
			? static::i()->$method(...$args) 
			: (method_exists(static::i()::class, $method)
				? (static::i()::class)::$method(...$args)
				: null
			)
		;

	}

	public static function i(){
		throw new Exception("La méthode 'i' n'existe pas dans la classe Facade `" . static::class . "`", 1);
	}
}