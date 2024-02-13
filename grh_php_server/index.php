<?php
define('ROOT', __DIR__);

require_once './core/helpers.php';

require_once './core/Facades/DeviceFacade.php';
require_once './core/Facades/DesktopFacade.php';

// dd(DesktopFacade::dd());

// DeviceFacade::setDeviceId('osososo');
// DeviceFacade::add();
// dd(DeviceFacade::get());






if($_GET['route'] == 'test'){echo "test-connexion-ok";exit;}

if($_GET['agent'] == 'desktop'){
	require_once __DIR__ . '/agents/desktop.php';
}
elseif($_GET['agent'] == 'mobile'){
	require_once __DIR__ . '/agents/mobile.php';
}