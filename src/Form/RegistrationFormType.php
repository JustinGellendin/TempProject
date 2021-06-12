<?php

namespace App\Form;

use App\Entity\User;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\IsTrue;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;

class RegistrationFormType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('username')
            ->add('plainPassword', PasswordType::class,
            [
                'mapped' => false,
                'constraints' => 
                [
                    new NotBlank(
                        [
                            'message' => 'Bitte trag ein Passwort ein!',
                        ]
                    ),
                    new Length(
                        [
                            'min' => 6,
                            'minMessage' => 'Das Passwort sollte mindestens {{ limit }} Zeichen enthalten',
                            'max' => 4096,
                        ]
                    ),
                ],
            ])
            ->add('repeatedPassword', PasswordType::class,
            [
                'mapped' => false,
                'constraints' => 
                [
                    new NotBlank(
                        [
                            'message' => 'Bitte wiederhole das Passwort!',
                        ]
                    ),
                ],
            ]
        );
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(
            [
                'data_class' => User::class,
            ]
        );
    }
}
