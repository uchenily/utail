// grab an element
var header = document.querySelector("header");
// construct an instance of Headroom, passing the element
var headroom = new Headroom(header, {
    // vertical offset in px before element is first unpinned
    offset: 70,
    // scroll tolerance in px before state changes
    tolerance: 0,
    classes: {
        initial: "animated",
        pinned: "slideDown",
        unpinned: "slideUp",
    }
});
// initialise
headroom.init();