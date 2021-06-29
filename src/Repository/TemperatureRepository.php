<?php

namespace App\Repository;

use App\Entity\Temperature;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;
use Doctrine\ORM\EntityManagerInterface;

class TemperatureRepository extends ServiceEntityRepository
{
    private $entityManager;
    
    public function __construct(ManagerRegistry $registry, EntityManagerInterface $entityManager)
    {
        parent::__construct($registry, Temperature::class);

        $this->entityManager = $entityManager;
    }

    public function getData()
    {
        $query = $this->createQueryBuilder('d')
        ->setMaxResults(10)
        ->getQuery()
        ->getResult();

        return $query;
    }
}
