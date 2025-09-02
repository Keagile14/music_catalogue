import java.util.*;

import com.google.gson.Gson;

import io.javalin.Javalin;

public class webapi {

    private static Map<Integer, Song> songs = new HashMap<>();
    private static int counter = 1;
    
    public static void main(String[] args){
        Javalin app = Javalin.create().start(7000);
        Gson gson = new Gson();

        app.get("/song/{id}", ctx ->{

            int id = Integer.parseInt(ctx.pathParam("id"));

            if (songs.containsKey(id)) {
                ctx.json(songs.get(id));
                
            } else{
                 ctx.status(404).result("Song not found");
            }
           
        });

        app.post("/song", ctx -> {

            Song newSong = gson.fromJson(ctx.body(),Song.class);

            boolean exists = songs.values().stream()
                    .anyMatch(s -> s.title.equalsIgnoreCase(newSong.title)
                                && s.artist.equalsIgnoreCase(newSong.artist));
            if (exists) {
                ctx.status(400).result("Duplicated song");
                
            }
// Save new song with generated ID
          int id = counter ++;
          songs.put(id,newSong);

          ctx.status(201)
          .header("Location","/song/"+ id)
          .json(newSong);

        });
    }

    public static class Song{
        int id;
        String title;
        String artist;
        String genre;
    }
}
