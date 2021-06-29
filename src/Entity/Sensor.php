<?php

namespace App\Entity;

use App\Repository\SensorRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=SensorRepository::class)
 */
class Sensor
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     */
    private $serverRack;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $adress;

    /**
     * @ORM\Column(type="float")
     */
    private $maxTemperature;

    /**
     * @ORM\ManyToOne(targetEntity=Manufacture::class, inversedBy="sensor")
     * @ORM\JoinColumn(nullable=false)
     */
    private $manufacture;

    /**
     * @ORM\OneToMany(targetEntity=Temperature::class, mappedBy="sensor")
     */
    private $temperature;

    /**
     * @ORM\OneToMany(targetEntity=Log::class, mappedBy="sensor")
     */
    private $log;

    public function __construct()
    {
        $this->temperature = new ArrayCollection();
        $this->log = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getServerRack(): ?int
    {
        return $this->serverRack;
    }

    public function setServerRack(int $serverRack): self
    {
        $this->serverRack = $serverRack;

        return $this;
    }

    public function getAdress(): ?string
    {
        return $this->adress;
    }

    public function setAdress(string $adress): self
    {
        $this->adress = $adress;

        return $this;
    }

    public function getMaxTemperature(): ?float
    {
        return $this->maxTemperature;
    }

    public function setMaxTemperature(float $maxTemperature): self
    {
        $this->maxTemperature = $maxTemperature;

        return $this;
    }

    public function getManufacture(): ?Manufacture
    {
        return $this->manufacture;
    }

    public function setManufacture(?Manufacture $manufacture): self
    {
        $this->manufacture = $manufacture;

        return $this;
    }

    /**
     * @return Collection|Temperature[]
     */
    public function getTemperature(): Collection
    {
        return $this->temperature;
    }

    public function addTemperature(Temperature $temperature): self
    {
        if (!$this->temperature->contains($temperature)) 
        {
            $this->temperature[] = $temperature;
            $temperature->setSensor($this);
        }

        return $this;
    }

    public function removeTemperature(Temperature $temperature): self
    {
        if ($this->temperature->removeElement($temperature)) 
        {
            if ($temperature->getSensor() === $this) 
            {
                $temperature->setSensor(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Log[]
     */
    public function getLog(): Collection
    {
        return $this->log;
    }

    public function addLog(Log $log): self
    {
        if (!$this->log->contains($log)) 
        {
            $this->log[] = $log;
            $log->setSensor($this);
        }

        return $this;
    }

    public function removeLog(Log $log): self
    {
        if ($this->log->removeElement($log)) 
        {
            if ($log->getSensor() === $this) 
            {
                $log->setSensor(null);
            }
        }

        return $this;
    }
}
