<?php

namespace App\Controller;

use App\Controller\BaseController;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Response;
use App\Entity\Temperature;

class DataController extends BaseController
{
    protected $entityManager;

    public function __construct(EntityManagerInterface $entityManager)
    {
        $this->entityManager = $entityManager;
        $this->temperatureRepository = $this->getRepository(Temperature::class);
    } 

    /**
     * @Route("/backend/data", name="data")
     */
    public function data():Response
    {
        VAR_DUMP($this->temperatureRepository->getData());

        return $this->render('backend/data.html.twig');
    }
}