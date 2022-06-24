(function($) {

	function initMap() {}

	$(document).ready(function() {
		"use strict";

		// TOOLTIP
		$('[data-toggle="tooltip"]').tooltip()

		// HAMBURGER
		$('.menu-btn').on('click', function(e) {
		$(".menu-btn").toggleClass("active");
		$(".bars .bar").toggleClass("rotated");
		$(".navbar .navbar-nav").toggleClass("active");

		});


		// SEARCH
		$('.search-btn').on('click', function(e) {
			$(".search-box").addClass("active")
			});
		$('.search-close-btn').on('click', function(e) {
			$(".search-box").removeClass("active")
			});

			// TOURS
			if ($('.tours').hasClass('tours-list')) {
					$('.tour-list-icon').css('color', '#ff9019');
			}

			if ($('.tours').hasClass('tours-grid')) {
					$('.tour-grid-icon').css('color', '#ff9019');
			}

			$(document).on('click', '.filter .dropdown-menu', function (e) {
  			e.stopPropagation();
			});

		$('.tour-view-type').on('click', function(e) {

				if($(e.target).hasClass('tour-list-icon') || $(e.target).is('.tour-list-icon i')) {
					$('.tour-list-icon').css('color', '#ff9019');
					$('.tour-grid-icon').css('color', '#bdc1c5');
					$('.tours').removeClass('tours-grid').addClass('tours-list');
					$('.tours .column').removeClass('col-lg-4').addClass('col-12');
					$('.tours .t-box').removeClass('tour-box').addClass('tour-box-list');
					e.preventDefault();
				}

				if($(e.target).hasClass('tour-grid-icon') || $(e.target).is('.tour-grid-icon i')) {
					$('.tour-grid-icon').css('color', '#ff9019');
					$('.tour-list-icon').css('color', '#bdc1c5');
					$('.tours').addClass('tours-grid').removeClass('tours-list');
					$('.tours .column').addClass('col-lg-4').removeClass('col-12');
					$('.tours .t-box').addClass('tour-box').removeClass('tour-box-list');
					e.preventDefault();
				}

		});

		// BLOG
		if ($('.posts').hasClass('blog-posts-list')) {
				$('.blog-list-icon').css('color', '#ff9019');
		}

		if ($('.posts').hasClass('blog-posts-grid')) {
				$('.blog-grid-icon').css('color', '#ff9019');
		}

		$('.blog-view-type').on('click', function(e) {

			if($(e.target).hasClass('blog-list-icon') || $(e.target).is('.blog-list-icon i')) {
				$('.blog-list-icon').css('color', '#ff9019');
				$('.blog-grid-icon').css('color', '#bdc1c5');
				$('.posts').removeClass('blog-posts-grid').addClass('blog-posts-list');
				$('.posts .column').removeClass('col-lg-4').addClass('col-12');
				$('.posts .post').removeClass('blog-post-grid').addClass('blog-post-list');
				e.preventDefault();
			}

			if($(e.target).hasClass('blog-grid-icon') || $(e.target).is('.blog-grid-icon i')){
				$('.blog-list-icon').css('color', '#bdc1c5');
				$('.blog-grid-icon').css('color', '#ff9019');
				$('.posts').addClass('tours-grid').removeClass('tours-list');
				$('.posts .column').addClass('col-lg-4').removeClass('col-12');
				$('.posts .post').addClass('blog-post-grid').removeClass('blog-post-list');
				e.preventDefault();
			}


		});

		// MOBILE MENU
		$('.navbar-nav .nav-item a').on('click', function () {
	  	$(this).parent().children('.navbar-nav .dropdown-menu, .navbar-nav .sub-dropdown-menu').slideToggle(300);
        return true;
	  	});


		});
		// END JQUERY

		// COUNTER
		if (!document.getElementById("counter")) {
		}
		else {

		var lastWasLower = false;
			$(document).scroll(function(){

			var p = $( "#counter" );
			var position = p.position();
			var position2 = position.top;

			if ($(document).scrollTop() > position2-300){
			if (!lastWasLower)
				$('#1').html('90368');
				$('#2').html('10759');
				$('#3').html('134116');
				$('#4').html('94329');

			lastWasLower = true;
				} else {
			lastWasLower = false;
			}
			});
		};


		$(".selectdrop .dropdown-menu li a").on('click', function(){
  		var selText = $(this).html();
  		$(this).parents('.selectdrop').find('.selection').html(selText + '<span class="arrow"><i class="fa fa-angle-down"></i></span>');
		});


		// WOW ANIMATION
		wow = new WOW(
      	{
       		animateClass: 'animated',
        	offset:       50
      	}
    	);
    	wow.init();


		// SLIDER
		var menu = ['WHO WE ARE<span>OUR PASSION</span>', 'NOTHERN LIGHTS<span>SPECIAL OFFER</span>', 'BEST SELLING<span>ICE CLIMBING</span>', 'THE FAMOUS<span>HIKING TRAILS</span>'];
		var interleaveOffset = 0.5;
		var swiperOptions = {
			loop: true,
			speed: 1000,
			parallax: true,
				autoplay: {
				delay: 3500,
				disableOnInteraction: false,
			  },
			grabCursor: true,
			watchSlidesProgress: true,
			pagination: {
			  el: '.swiper-custom-pagination',
					clickable: true,
					renderBullet: function (index, className) {
				  return '<span class="' + className + '">' + (menu[index]) + '</span>';
				},
			},
			on: {
			progress: function() {
			  var swiper = this;
			  for (var i = 0; i < swiper.slides.length; i++) {
				var slideProgress = swiper.slides[i].progress;
				var innerOffset = swiper.width * interleaveOffset;
				var innerTranslate = slideProgress * innerOffset;
				swiper.slides[i].querySelector(".slide-inner").style.transform =
				  "translate3d(" + innerTranslate + "px, 0, 0)";
			  }
			},
			touchStart: function() {
			  var swiper = this;
			  for (var i = 0; i < swiper.slides.length; i++) {
				swiper.slides[i].style.transition = "";
			  }
			},
			setTransition: function(speed) {
			  var swiper = this;
			  for (var i = 0; i < swiper.slides.length; i++) {
				swiper.slides[i].style.transition = speed + "ms";
				swiper.slides[i].querySelector(".slide-inner").style.transition =
				  speed + "ms";
			  }
			}
		  }
		};

		var swiper = new Swiper(".swiper-container", swiperOptions);



		// DATA BACKGROUND IMAGE
		var pageSection = $(".bg-image");
		pageSection.each(function(indx){
			if ($(this).attr("data-background")){
				$(this).css("background-image", "url(" + $(this).data("background") + ")");
			}
    	});


		// CAROUSEL
		var swiper2 = new Swiper('.swiper-carousel', {
		  slidesPerView: 3,
		  spaceBetween: 14,
		   // Navigation arrows
		navigation: {
		  nextEl: '.swiper-button-next',
		  prevEl: '.swiper-button-prev',
		},
			breakpoints: {
					1024: {
					  slidesPerView: 3,
					  spaceBetween: 14,
					},
					768: {
					  slidesPerView: 2,
					  spaceBetween: 14,
					},
					640: {
					  slidesPerView: 1,
					  spaceBetween: 14,
					},
					320: {
					  slidesPerView: 1,
					  spaceBetween: 0,
					}
				  }
		});

		// REVIEWS
		var swiper3 = new Swiper('.swiper-reviews', {
		  slidesPerView: 3,
		  spaceBetween: 30,
		   // Navigation arrows
	   pagination: {
			el: '.swiper-pagination',
		   clickable: true
		  },
			breakpoints: {
					1023: {
					  slidesPerView: 2,
					  spaceBetween: 30,
					},
					768: {
					  slidesPerView: 2,
					  spaceBetween: 30,
					},
					640: {
					  slidesPerView: 1,
					  spaceBetween: 30,
					},
					320: {
					  slidesPerView: 1,
					  spaceBetween: 0,
					}
				  }
		});


		// BLOG
		var swiper4 = new Swiper('.swiper-blog-carousel', {
			slidesPerView: '2',
			centeredSlides: true,
			loop: true,
			spaceBetween: 0,
			navigation: {
			  nextEl: '.swiper-button-next',
			  prevEl: '.swiper-button-prev',
			},
			breakpoints: {
					768: {
					  slidesPerView: 1,
					  spaceBetween: 14,
					},
					640: {
					  slidesPerView: 1,
					  spaceBetween: 14,
					},
					320: {
					  slidesPerView: 1,
					  spaceBetween: 0,
					}
				  }
		});


		// ATTRACTION
		var swiper5 = new Swiper('.attraction-carousel', {
		  slidesPerView: 4,
		  spaceBetween: 15,
			loop: true,
		   // Navigation arrows
		navigation: {
		  nextEl: '.swiper-button-next',
		  prevEl: '.swiper-button-prev',
		},
			breakpoints: {
					1024: {
					  slidesPerView: 4,
					  spaceBetween: 14,
					},
					768: {
					  slidesPerView: 2,
					  spaceBetween: 14,
					},
					640: {
					  slidesPerView: 1,
					  spaceBetween: 14,
					},
					320: {
					  slidesPerView: 1,
					  spaceBetween: 0,
					}
				  }
		});




})(jQuery);
