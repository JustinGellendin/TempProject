<?php

namespace App\Entity;

use App\Repository\SensorsRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=SensorsRepository::class)
 */
class Sensors
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
     * @ORM\ManyToOne(targetEntity=Manufacture::class, inversedBy="sensors")
     * @ORM\JoinColumn(nullable=false)
     */
    private $manufacture;

    /**
     * @ORM\OneToMany(targetEntity=Temperatures::class, mappedBy="sensor")
     */
    private $temperatures;

    /**
     * @ORM\OneToMany(targetEntity=Logs::class, mappedBy="sensor")
     */
    private $logs;

    public function __construct()
    {
        $this->temperatures = new ArrayCollection();
        $this->logs = new ArrayCollection();
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
     * @return Collection|Temperatures[]
     */
    public function getTemperatures(): Collection
    {
        return $this->temperatures;
    }

    public function addTemperature(Temperatures $temperature): self
    {
        if (!$this->temperatures->contains($temperature)) {
            $this->temperatures[] = $temperature;
            $temperature->setSensor($this);
        }

        return $this;
    }

    public function removeTemperature(Temperatures $temperature): self
    {
        if ($this->temperatures->removeElement($temperature)) {
            // set the owning side to null (unless already changed)
            if ($temperature->getSensor() === $this) {
                $temperature->setSensor(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Logs[]
     */
    public function getLogs(): Collection
    {
        return $this->logs;
    }

    public function addLog(Logs $log): self
    {
        if (!$this->logs->contains($log)) {
            $this->logs[] = $log;
            $log->setSensor($this);
        }

        return $this;
    }

    public function removeLog(Logs $log): self
    {
        if ($this->logs->removeElement($log)) {
            // set the owning side to null (unless already changed)
            if ($log->getSensor() === $this) {
                $log->setSensor(null);
            }
        }

        return $this;
    }
}
