<?php

namespace App\Controller;

use App\Entity\User;
use App\Controller\BaseController;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use App\Form\RegistrationFormType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Response;

class UserController extends BaseController
{
    private $userRepository;

    public function __construct(EntityManagerInterface $entityManager)
    {
        parent::__construct($entityManager);

        $this->userRepository = $this->getRepository(User::class);
    } 

    /**
     * @Route("/backend/users", name="user-list")
     */
    public function users():Response
    {
        $allUsers = $this->userRepository->findAll();

        return $this->render('backend/user.html.twig', 
            [
                'users' => $allUsers,
            ]
        );
    }

    /**
     * @Route("/backend/create", name="user-create")
     */
    public function create(Request $request, UserPasswordEncoderInterface $passwordEncoder)
    {
        $user = new User();
        $form = $this->createForm(RegistrationFormType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) 
        {
            if ($form->get('plainPassword') !== $form->get('repeatedPassword'))
            {
                return $this->addFlash(
                    'notice',
                    'ungleiches passwort'
                );
            }

            $user->setPassword(
                $passwordEncoder->encodePassword(
                    $user,
                    $form->get('plainPassword')->getData()
                )
            )
            ->setRegistrationDate(new \DateTime('now'));;

            $this->entityManager->persist($user);
            $this->entityManager->flush();

            return $this->redirectToRoute('user-list');
        }

        return $this->render('backend/user-creation.html.twig', 
            [
                'registrationForm' => $form->createView(),
            ]
        );
    }

    /**
     * @Route("/backend/edit/{id}", name="user-edit")
     */
    public function edit(int $id):Response
    {
        $currentUser = $this->userRepository->findBy(['id' => $id]);

        return $this->render('backend/user-edit.html.twig');
    }

    /**
     * @Route("/backend/delete/{id}", name="user-delete")
     */
    public function delete(int $id): Response
    {
        $userToDelete = $this->userRepository->findOneBy(['id' => $id]);

        $this->entityManager->remove($userToDelete);
        $this->entityManager->flush();

        return $this->redirectToRoute('user-list');
    }

    /**
     * @Route("/backend/toggleActiveState/{id}", name="user-active")
     */
    public function toggleActiveState(int $id): Response
    {
        $userToToggle = $this->userRepository->findOneBy(['id' => $id]);
        $state = $userToToggle->getActivated();

        $userToToggle->setActivated(!$state);

        $this->entityManager->persist($userToToggle);
        $this->entityManager->flush();

        return $this->redirectToRoute('user-list');
    }

}