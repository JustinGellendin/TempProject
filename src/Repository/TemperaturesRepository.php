<?php

namespace App\Repository;

use App\Entity\Temperatures;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Temperatures|null find($id, $lockMode = null, $lockVersion = null)
 * @method Temperatures|null findOneBy(array $criteria, array $orderBy = null)
 * @method Temperatures[]    findAll()
 * @method Temperatures[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class TemperaturesRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Temperatures::class);
    }

    // /**
    //  * @return Temperatures[] Returns an array of Temperatures objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('t')
            ->andWhere('t.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('t.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Temperatures
    {
        return $this->createQueryBuilder('t')
            ->andWhere('t.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
