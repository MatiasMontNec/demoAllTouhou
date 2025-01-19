import React from 'react';
import { Box, Divider, List, ListItemButton, ListItemIcon, ListItemText, Drawer } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import HomeIcon from '@mui/icons-material/Home';

export default function Sidemenu({ open, toggleDrawer }) {
    const navigate = useNavigate();

    const listOptions = () => (
        <Box
            role="presentation"
            onClick={toggleDrawer(false)}
        >
            <List>
                <ListItemButton onClick={() => navigate("")}>
                    <ListItemIcon>
                        <HomeIcon />
                    </ListItemIcon>
                    <ListItemText primary="Inicio ᗜ˰ᗜ" />
                </ListItemButton>
            </List>

            <Divider />
        </Box>
    );

    return (
        <div>
            <Drawer anchor={"left"} open={open} onClose={toggleDrawer(false)}>
                {listOptions()}
            </Drawer>
        </div>
    );
}
