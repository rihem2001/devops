package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)

class UniversityServiceImplMockTest {



    @Mock
    UniversiteRepository universiteRepository;


    @InjectMocks
    UniversiteServiceImpl universiteService;

    Universite universite = new Universite("f1", "l1", "adresse1");


    List<Universite> universitesList = new ArrayList<>() {
        {
            add(new Universite(2L, "ENIT", "Rue d’Ingénieur", null));
            add(new Universite(3L, "IHEC", "Rue de Commerce", null));
        }
    };

    @Test
     void testRetrieveUser() {
        Mockito.when(universiteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(universite));
        Universite user1 = universiteService.retrieveUniversite(2l);
        Assertions.assertNotNull(user1);


    }

    @Test
     void testRetrieveAllUniversites() {
        // Mocking the behavior of the repository
        Mockito.when(universiteRepository.findAll()).thenReturn(universitesList);

        // Retrieving all Universites
        List<Universite> retrievedUniversites = universiteService.retrieveAllUniversites();

        // Assertions to check that the retrieved list is correct
        Assertions.assertNotNull(retrievedUniversites);
        Assertions.assertEquals(2, retrievedUniversites.size());
        Assertions.assertEquals("ENIT", retrievedUniversites.get(0).getNomUniversite());
    }

    @Test
     void testAddUniversite() {

        Universite universite = new Universite();
        universite.setNomUniversite("Université ESPRIT");
        universite.setAdresse("Some Address");

        // Mocking the behavior of the repository's save method
        Mockito.when(universiteRepository.save(universite)).thenReturn(universite);

        // Adding the Universite
        Universite savedUniversite = universiteService.addUniversite(universite);

        // Assertions to verify the Universite was saved with the correct name
        Assertions.assertNotNull(savedUniversite);
        Assertions.assertEquals("Université ESPRIT", savedUniversite.getNomUniversite());
    }


    @Test
     void testModifyUniversite() {
        // Mocking the behavior of the repository to save the Universite
        Mockito.when(universiteRepository.save(universite)).thenReturn(universite);

        // Modifying the Universite name
        universite.setNomUniversite("Updated ESPRIT");
        Universite modifiedUniversite = universiteService.modifyUniversite(universite);

        // Assertions to check that the modifications were applied
        Assertions.assertNotNull(modifiedUniversite);
        Assertions.assertEquals("Updated ESPRIT", modifiedUniversite.getNomUniversite());
    }

    @Test
     void testRemoveUniversite() {
        // Mocking the repository's delete behavior
        Mockito.doNothing().when(universiteRepository).deleteById(1L);

        // Removing the Universite by ID
        universiteService.removeUniversite(1L);

        // Verifying that the deleteById method was called once
        Mockito.verify(universiteRepository, Mockito.times(1)).deleteById(1L);
    }


}







