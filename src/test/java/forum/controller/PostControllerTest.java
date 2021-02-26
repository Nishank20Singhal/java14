package forum.controller;


import forum.Main;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import forum.model.Post;
import forum.service.ForumCrudService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ForumCrudService service;

    @Test
    @WithMockUser
    public void shouldReturnNewPost() throws Exception {
        this.mockMvc.perform(post("/save").param("name", "new post"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(service).save(argument.capture());
        assertThat(argument.getValue().getName(), is("new post"));
    }

    @Test
    @WithMockUser
    public void shouldReturnMessageCreate() throws Exception {
        this.mockMvc.perform(get("/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/create"));
    }

    @Test
    @WithMockUser
    public void shouldReturnMessageEdit() throws Exception {
        this.mockMvc.perform(get("/edit?id=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/edit"));
    }

    @Test
    @WithMockUser
    public void shouldReturnMessageTopic() throws Exception {
        this.mockMvc.perform(get("/topic?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("topic"));
    }
}