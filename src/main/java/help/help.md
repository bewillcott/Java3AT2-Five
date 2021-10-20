@@@
use: articles2
title: Java3 AT2 Five
@@@

# Java3 AT2 Five

This program was developed for my Diploma in Software Development at the 
South Metropolitan TAFE, Rockingham WA.

This application provides "Drag-N-Drop" functionality between two lists, 
has a 2D Graphic arrow, and has this help facility.

## Drag-N-Drop

This functionality is available between the two lists.

To use, click and drag an item from one list to the other.  This will cause
the item to be moved accordingly from the originating list to the destination 
list.

An item can be moved back and forth in this manner any number of times.  For
instance, you could use this method to manually sort each list as you may need.

## 2D Arrow

The arrow that is situated between the lists, is used as a visual indicator of
the Drag-N-Drop process.

- **Black** pointing to the right
    - No DND is in process
- **RED** pointing in either direction
    - DND process has started
    - DND process cannot be completed successfully over the _source_
    - Arrow is pointing to the only possible _target_ for the DND process
- **ORANGE** pointing in either direction
    - The mouse pointer is currently not over either of the lists
    - To show that the process is still going
    - Arrow is pointing to the only possible _target_ for the DND process
- **GREEN** pointing in either direction
    - Mouse is over the _target_ list
    - DND can be completed whilst the arrow is green

Dropping the item when the arrow is not green, or pressed the [Esc] key, will
cause an incomplete termination of the DND process, and the item will
remain in its original place.
