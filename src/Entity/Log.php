<?php

namespace App\Entity;

use App\Repository\LogRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=LogRepository::class)
 */
class Log
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity=User::class, inversedBy="log")
     * @ORM\JoinColumn(nullable=false)
     */
    private $user;

    /**
     * @ORM\ManyToOne(targetEntity=Sensor::class, inversedBy="log")
     * @ORM\JoinColumn(nullable=false)
     */
    private $sensor;

    /**
     * @ORM\Column(type="datetime")
     */
    private $creationDate;

    /**
     * @ORM\Column(type="float", nullable=true)
     */
    private $oldMaxTemperature;

    /**
     * @ORM\Column(type="float")
     */
    private $newMaxTemperature;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;

        return $this;
    }

    public function getSensor(): ?Sensor
    {
        return $this->sensor;
    }

    public function setSensor(?Sensor $sensor): self
    {
        $this->sensor = $sensor;

        return $this;
    }

    public function getCreationDate(): ?\DateTimeInterface
    {
        return $this->creationDate;
    }

    public function setCreationDate(\DateTimeInterface $creationDate): self
    {
        $this->creationDate = $creationDate;

        return $this;
    }

    public function getOldMaxTemperature(): ?float
    {
        return $this->oldMaxTemperature;
    }

    public function setOldMaxTemperature(?float $oldMaxTemperature): self
    {
        $this->oldMaxTemperature = $oldMaxTemperature;

        return $this;
    }

    public function getNewMaxTemperature(): ?float
    {
        return $this->newMaxTemperature;
    }

    public function setNewMaxTemperature(float $newMaxTemperature): self
    {
        $this->newMaxTemperature = $newMaxTemperature;

        return $this;
    }
}
