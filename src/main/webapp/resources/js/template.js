jQuery(document).ready(function($){
	var secondaryNav = $('.cd-secondary-nav'),
		secondaryNavTopPosition = secondaryNav.offset().top,
		contentSections = $('.cd-section');
	
	//on mobile - open/close secondary navigation clicking/tapping the .cd-secondary-nav-trigger
	$('.cd-secondary-nav-trigger').on('click', function(event){
		event.preventDefault();
		$(this).toggleClass('menu-is-open');
		secondaryNav.find('ul').toggleClass('is-visible');
	});

	//smooth scrolling when clicking on the secondary navigation items
	secondaryNav.find('ul a').on('click', function(event){
	    event.preventDefault();
	    var target= $(this.hash);
	    $('body,html').animate({
	    	'scrollTop': target.offset().top - secondaryNav.height() + 1
	    	}, 400
	    ); 
	    //on mobile - close secondary navigation
	    $('.cd-secondary-nav-trigger').removeClass('menu-is-open');
	    secondaryNav.find('ul').removeClass('is-visible');
	});

	//on mobile - open/close primary navigation clicking/tapping the menu icon
	$('.cd-primary-nav').on('click', function(event){
		if($(event.target).is('.cd-primary-nav')) $(this).children('ul').toggleClass('is-visible');
	});
});