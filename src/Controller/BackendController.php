<?php

namespace App\Controller;

use App\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Response;

class BackendController extends AbstractController
{
    /**
     * @Route("/backend", name="backend")
     */
    public function backend():Response
    {
        $lastLogin = null;
        $user = $this->getUser();

        if ($user)
        {
            $lastLogin = $user->getLastLogin();
        }

        return $this->render('backend/start.html.twig',
            [
                'lastLogin' => $lastLogin
            ]
        );
    }
}