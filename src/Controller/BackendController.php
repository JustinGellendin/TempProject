<?php

namespace App\Controller;

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
        if ($user && $user instanceof User){
            $lastLogin = $user->getLastLogin();
        }

        return $this->render('backend/start.html.twig',
        [
            'lastLogin' => $lastLogin
        ]);
    }

    /**
     * @Route("/backend/users", name="users")
     */
    public function users():Response
    {
        return $this->render('backend/user.html.twig');
    }

        /**
     * @Route("/backend/tempratures", name="tempratures")
     */
    public function tempratures():Response
    {
        return $this->render('backend/temps.html.twig');
    }
}