<?php

namespace App\Controller;

use App\Entity\User;
use App\Controller\BaseController;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Response;
use Symfony\UX\Chartjs\Builder\ChartBuilderInterface;
use Symfony\UX\Chartjs\Model\Chart;

class TemperatureController extends BaseController
{
    /**
     * @Route("/backend/temperatures", name="temperatures")
     */
    public function temperatures(ChartBuilderInterface $chartBuilder): Response
    {
        $chart = $chartBuilder->createChart(Chart::TYPE_LINE);
        $chart->setData(
            [
            'labels' => ['0 Uhr', '0 Uhr', '0 Uhr', '0 Uhr', '0 Uhr', '0 Uhr', '0 Uhr'],
            'datasets' => [
                [
                    'label' => 'Temperatur',
                    'backgroundColor' => 'rgb(255, 99, 132)',
                    'borderColor' => 'rgb(255, 99, 132)',
                    'data' => [0, 10, 5, 2, 20, 30, 45],
                ],
            ],
        ]);

        $chart->setOptions(
            [
                'responsive' => true,
                'maintainAspectRatio' => true,
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

        return $this->render('backend/temps.html.twig', [
            'chart' => $chart
        ]);
    }
}