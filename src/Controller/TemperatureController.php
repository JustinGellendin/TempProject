<?php

namespace App\Controller;

use App\Entity\User;
use App\Controller\BaseController;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Response;

class TemperatureController extends BaseController
{
    /**
     * @Route("/backend/temperatures", name="temperatures")
     */
    public function temperatures():Response
    {
        return $this->render('backend/temps.html.twig');
    }
}