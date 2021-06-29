<?php

namespace App\Controller;

use App\Controller\BaseController;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Response;
use Symfony\UX\Chartjs\Builder\ChartBuilderInterface;
use Symfony\UX\Chartjs\Model\Chart;
use Doctrine\ORM\EntityManagerInterface;
use App\Entity\Temperature;

class TemperatureController extends BaseController
{
    private $temperatureRepository;

    public function __construct(EntityManagerInterface $entityManager)
    {
        parent::__construct($entityManager);

        $this->temperatureRepository = $this->getRepository(Temperature::class);
    } 

    /**
     * @Route("/backend/graph", name="graph")
     */
    public function graph(ChartBuilderInterface $chartBuilder): Response
    {
        $chart = $chartBuilder->createChart(Chart::TYPE_LINE);
        $labels = $this->getLabels();
        $data = $this->getData();

        $chart->setData(
            [
                'labels' => $labels,
                'datasets' => 
            [        
                    [
                        'label' => 'Temperatur',
                        'backgroundColor' => 'rgb(255, 99, 132)',
                        'borderColor' => 'rgb(255, 99, 132)',
                        'data' => $data,
                    ],
                ],
            ]
        );

        $chart->setOptions(
            [
                'scaleGridLineColor' => "rgba(225, 255, 255, 0.02)",
                'scaleOverride' => true,
                'animation' => true,
                'responsive' => true,
                'maintainAspectRatio' => true,
                'tooltips' => [
                    'mode' => 'index',
                    'intersect' => false
                ],
                'scales' => 
                [
                    'yAxes' => 
                    [
                        [
                            'ticks' => ['min' => 0, 'max' => 100]
                        ],
                    ],
                ],
            ]
        );

        return $this->render('backend/temps.html.twig', 
            [
                'chart' => $chart
            ]
        );
    }

    private function getData()
    {
        $temperatures = $this->temperatureRepository->findAll();
        $data = [];

        foreach ($temperatures as $temperature)
        {
            $data[] = $temperature->getTemperature();
        }

        return $data;
    }

    private function getLabels()
    {
        $temperatures = $this->temperatureRepository->findAll();
        $labels = [];

        foreach ($temperatures as $temperature)
        {
            $labels[] = $temperature->getCreationTime()->format('H:i:s');
        }

        return $labels;
    }
}