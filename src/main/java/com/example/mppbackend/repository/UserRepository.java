package com.example.mppbackend.repository;

import com.example.mppbackend.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepository implements Repository<User, Integer> {

    List<User> users = new ArrayList<>(List.of(
            new User(1, "Rosetta.Mante47", "ouvKJoluZ7NoCbi", "Kennedi_Fritsch16@yahoo.com",
                    "https://avatars.githubusercontent.com/u/12451586", "122.181.149.249"),
            new User(2, "Justina5", "H37Lk_XpNAh3tBX", "Vicenta40@hotmail.com",
                    "https://avatars.githubusercontent.com/u/50303189", "199.91.34.103"),
            new User(3, "Lon_Kiehn", "3sBbKWTHD4suIPQ", "Alisha67@yahoo.com",
                    "https://avatars.githubusercontent.com/u/7457114", "142.74.30.1"),
            new User(4, "Jabari_Conroy28", "pZ9tpSNatpNrqfN", "Gerda.Gottlieb@yahoo.com",
                    "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/944.jpg", "118.255.70.168"),
            new User(5, "Trinity4", "dSvo4AuNcAbRMra", "Garnett_Hermann87@yahoo.com",
                    "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/111.jpg", "14.45.93.189"),
            new User(6, "Alena52", "ZEwLrDpu723Wwv7", "Gianni21@yahoo.com",
                    "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/307.jpg", "135.141.64.243")));

    @Override
    public User getById(Integer id) {
        return users.stream().filter(u -> Objects.equals(u.getId(), id)).findFirst().get();
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public User update(Integer id, User updatedUser) {
        User currentUser = getById(id);

        currentUser.setUsername(updatedUser.getUsername());
        currentUser.setPassword(updatedUser.getPassword());
        currentUser.setEmail(updatedUser.getEmail());
        currentUser.setAvatar(updatedUser.getAvatar());
        currentUser.setIp(updatedUser.getIp());

        return currentUser;
    }

    @Override
    public void delete(Integer id) {
        users.removeIf(u -> Objects.equals(u.getId(), id));
    }

    @Override
    public boolean existsById(Integer id) {
        return users.stream().anyMatch(u -> u.getId() == id);
    }

    @Override
    public int count() {
        return users.size();
    }

    @Override
    public List<User> getAll() {
        return users;
    }
}
