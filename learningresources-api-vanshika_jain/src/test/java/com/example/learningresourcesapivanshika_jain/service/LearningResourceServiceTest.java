package com.example.learningresourcesapivanshika_jain.service;

import com.example.learningresourcesapivanshika_jain.entity.LearningResource;
import com.example.learningresourcesapivanshika_jain.entity.LearningResourceStatus;
import com.example.learningresourcesapivanshika_jain.repository.LearningResourceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class LearningResourceServiceTest {
    @Mock
    LearningResourceRepository learningResourceRepository;
    @InjectMocks
    LearningResourceService learningResourceService;

    @Test
    public void getProfitMarginsOfAllTheAvailableLearningResources()
    {
        List<LearningResource> learningResourceList=new ArrayList<>();
        LearningResource learningResource1=new LearningResource(106,"Test1",300.0,500.0, LearningResourceStatus.LIVE,LocalDate.now(),LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        LearningResource learningResource2=new LearningResource(107,"Test2",500.0,600.0, LearningResourceStatus.LIVE,LocalDate.now(),LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));

        learningResourceList.add(learningResource1);
        learningResourceList.add(learningResource2);
        List<Double> expectedProfitMargins;
        expectedProfitMargins=new ArrayList<>();
        for(LearningResource lr1:learningResourceList)
        {Double profit=(lr1.getSellingPrice()-lr1.getCostPrice())/lr1.getSellingPrice();
            expectedProfitMargins.add(profit);}

        when(learningResourceRepository.findAll()).thenReturn(learningResourceList);
        List<Double> actualProfitMargins=learningResourceService.getProfitMargin();
        assertEquals(expectedProfitMargins,actualProfitMargins,"wrong profit margins");
    }

    @Test
    public void sortTheLearningResourceBasedOnProfitMarginInNonIncreasingOrder()
    {
        LearningResource learningResource1=new LearningResource(106,"Test1",300.0,500.0, LearningResourceStatus.LIVE,LocalDate.now(),LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        LearningResource learningResource2=new LearningResource(107,"Test2",500.0,600.0, LearningResourceStatus.LIVE,LocalDate.now(),LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));

        List<LearningResource> learningResourceList = new ArrayList<>();
        learningResourceList.add(learningResource1);
        learningResourceList.add(learningResource2);

        learningResourceList.sort(new LearningResourceSorter());

        when(learningResourceRepository.findAll()).thenReturn(learningResourceList);
        List<LearningResource> learningResourceSorted=learningResourceService.sortLearningResources();
        assertEquals(learningResourceList,learningResourceSorted,"The learning resources are not sorted by profit margin");
    }

    @Test
    public void saveTheLearningResources()
    {
        List<LearningResource> learningResourceList=new ArrayList<>();
        LearningResource learningResource1=new LearningResource(106,"Test1",300.0,500.0, LearningResourceStatus.LIVE,LocalDate.now(),LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        LearningResource learningResource2=new LearningResource(107,"Test2",500.0,600.0, LearningResourceStatus.LIVE,LocalDate.now(),LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
        LearningResource learningResource3=new LearningResource(108,"Test3",500.0,600.0, LearningResourceStatus.LIVE,LocalDate.now(),LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));

        learningResourceList.add(learningResource1);
        learningResourceList.add(learningResource2);
        learningResourceList.add(learningResource3);

        learningResourceService.saveLearningResources(learningResourceList);
        verify(learningResourceRepository,times(learningResourceList.size())).save(any(LearningResource.class));
    }


    @Test
    public void deleteLearningResourceByID()
    {
        int learningResourceId=106;
        learningResourceService.deleteLearningResource(learningResourceId);
        verify(learningResourceRepository,times(1)).deleteById(learningResourceId);
    }
}
