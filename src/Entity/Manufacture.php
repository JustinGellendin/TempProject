<?php

namespace App\Entity;

use App\Repository\ManufactureRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ManufactureRepository::class)
 */
class Manufacture
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $name;

    /**
     * @ORM\OneToMany(targetEntity=Sensor::class, mappedBy="manufacture")
     */
    private $sensor;

    public function __construct()
    {
        $this->sensor = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

    /**
     * @return Collection|Sensor[]
     */
    public function getSensor(): Collection
    {
        return $this->sensor;
    }

    public function addSensor(Sensor $sensor): self
    {
        if (!$this->sensor->contains($sensor)) 
        {
            $this->sensor[] = $sensor;
            $sensor->setManufacture($this);
        }

        return $this;
    }

    public function removeSensor(Sensor $sensor): self
    {
        if ($this->sensor->removeElement($sensor)) 
        {
            if ($sensor->getManufacture() === $this) 
            {
                $sensor->setManufacture(null);
            }
        }

        return $this;
    }
}
