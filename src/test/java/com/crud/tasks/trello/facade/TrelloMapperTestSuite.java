package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "name1", true));
        trelloListDtos.add(new TrelloListDto("1", "name2", false));
        trelloBoardDtos.add(new TrelloBoardDto("1", "Board",  trelloListDtos));
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);
        int size = trelloBoards.size();
        String nameResults = trelloBoards.get(0).getName();
        boolean isFirstListClosed = trelloBoards.get(0).getLists().get(0).isClosed();
        //Then
        Assert.assertEquals(1, size);
        Assert.assertEquals("Board", nameResults);
        Assert.assertTrue(isFirstListClosed);

    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "list1", true));
        trelloLists.add(new TrelloList("2", "list2", false));
        trelloBoards.add(new TrelloBoard("1", "board1", trelloLists));
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);
        int size = trelloBoardDtos.size();
        String nameBoard = trelloBoardDtos.get(0).getName();
        String nameList = trelloBoardDtos.get(0).getLists().get(0).getName();
        //Then
        Assert.assertEquals(1, size);
        Assert.assertEquals("board1", nameBoard);
        Assert.assertEquals("list1", nameList);
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "name1", false));
        trelloListDtos.add(new TrelloListDto("2", "name2", false));
        trelloListDtos.add(new TrelloListDto("3", "name3", false));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        int size = trelloLists.size();
        String idResults = trelloLists.get(2).getId();
        //Then
        Assert.assertEquals(3, size);
        Assert.assertEquals("3", idResults);
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "name1", false));
        trelloLists.add(new TrelloList("2", "name2", false));
        trelloLists.add(new TrelloList("3", "name3", false));
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        int size = trelloListDtos.size();
        String nameResults = trelloListDtos.get(0).getName();
        //Then
        Assert.assertEquals(3, size);
        Assert.assertEquals("name1", nameResults);
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card1", "text", "top", "test");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        String nameResult = trelloCard.getName();
        String testIdResult = trelloCard.getListId();
        //Then
        Assert.assertEquals("card1", nameResult);
        Assert.assertEquals("test", testIdResult);
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card2", "text", "top", "test");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        String nameResult = trelloCardDto.getName();
        String posResult = trelloCardDto.getPos();
        //Then
        Assert.assertEquals("card2", nameResult);
        Assert.assertEquals("top", posResult);
    }

    @Test
    public void shouldReturnTesting(){
        //Given
        TrelloCard trelloCard = new TrelloCard("test", "test", "top", "1");
        //When
        String result = trelloValidator.validateCard(trelloCard);
        //Then
        Assert.assertEquals("Someone is testing my application!", result);
    }
}